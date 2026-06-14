package com.tankiq.iam.application.queryservices;

import com.tankiq.iam.domain.model.aggregates.User;
import com.tankiq.iam.domain.model.queries.GetAllUsersQuery;
import com.tankiq.iam.domain.model.queries.GetUserByIdQuery;
import com.tankiq.shared.application.result.ApplicationError;
import com.tankiq.shared.application.result.Result;

import java.util.List;

public interface UserQueryService {
    Result<List<User>, ApplicationError> handle(GetAllUsersQuery query);
    Result<User, ApplicationError> handle(GetUserByIdQuery query);
}
