package com.tankiq.iam.infrastructure.security.haveibeenpwned;

import com.tankiq.iam.application.internal.outboundservices.security.PasswordBreachCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class HaveIBeenPwnedService implements PasswordBreachCheckService {

    private static final String API_URL = "https://api.pwnedpasswords.com/range/";
    private final RestTemplate restTemplate;

    public HaveIBeenPwnedService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public boolean isPasswordBreached(String rawPassword) {
        try {
            String sha1Hash = sha1Hex(rawPassword).toUpperCase();
            String prefix = sha1Hash.substring(0, 5);
            String suffix = sha1Hash.substring(5);

            String response = restTemplate.getForObject(API_URL + prefix, String.class);
            if (response == null) {
                return false;
            }

            return response.lines().anyMatch(line -> line.startsWith(suffix));
        } catch (Exception e) {
            log.error("Could not verify password breach status: {}", e.getMessage());
            return false;
        }
    }

    private String sha1Hex(String input) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
