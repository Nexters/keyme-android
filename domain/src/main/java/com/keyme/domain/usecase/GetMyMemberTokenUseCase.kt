package com.keyme.domain.usecase

import javax.inject.Inject

class GetMyMemberTokenUseCase @Inject constructor() {

    operator fun invoke(): String {
        return "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhY2Nlc3NUb2tlbiIsImlhdCI6MTY5MTg0MjM1NiwiZXhwIjoxNjk0NDM0MzU2LCJtZW1iZXJJZCI6Miwicm9sZSI6IlJPTEVfVVNFUiJ9.bLUl_ObvXr2pkLGNBZYWbJgLZLo3P0xB2pawckRGYZM"
    }
}
