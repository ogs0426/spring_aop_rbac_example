## Identity Service that supports OpenID Connect

### Subjects

- Account(계정) : 'uuid'를 소지하며 '회원'이 '권한'을 획득 하기 위한 신원을 증명 하기 위한 data로 사용 되고 있습니다.
- User(사용자) : 일반적인 회원의 '사용자'를 뜻하며 해당 서비스에서는 'userinfo' Personal Information의 의미로 사용 되고 있습니다.
    - 사용자의 이용권 내역, 좋아하는 리그 등의 회원 고객 Data 매핑의 기준이 되는 설정 값입니다.
    - 해당 Data는 "CI"를 보유 하고 있어야 하며 'v2'기준 User는 없을 수 있십니다.
        - 'CI'는 User Data Insert, Update의 기준이 됩니다.

> User : Account = 1 : N

### CI issuance (tid 발급)

Authentication Transaction API : GET [https://nid-dev.spotvnow.co.kr/api/v3/identity/thirdparty/kg](https://nid-dev.spotvnow.co.kr/api/v3/identity/thirdparty/kg)

Ok Redirect Url API : POST [https://nid-dev.spotvnow.co.kr/api/v3/identity/ci/injection](https://nid-dev.spotvnow.co.kr/api/v3/identity/ci/injection)

Ok\_Res : `{ "birth": "string", "name": "string", "phone": "string", "sex": "string", "tid": "string" }`

`tid` 발급은 CI 인증 위의 'Authentication Transaction API' 통하여 이루어 집니다.

Sign Check Error : `{ "status_code": 400, "error": "Bad Request", "error_description": "Not a valid signature.", "error_hint": "SPI00009" }`

> 잘못된 인증 경로 일 경우 위의 Error Code가 발생 될 수 있습니다.

### Tables (설정 관리)

- account\_provider
- user\_info

### Mapping (계정 연결)

해당 Mapping은 Update가 존재 하지 않는다.

- account\_connection : Account간의 '대표 계정'을 설정 합니다.
    - `account_provider`가 없다면 해당 table또한 없습니다.
    - `SPOTV` : 일반적으로 **대표 계정**은 `SPOTV`입니다.
    - `etc` : 정책에 따라 `SPOTV`가 아닌 `provider_type`이 대표 계정이 될 수 있습니다.
        - 이 경우 일부 기능이 제한 될 수 있습니다.
        - `SPOTV`가 아닌 `provider_type`이 대표 계정이 될 경우 이는 **자신만을 연결** 할 수 있습니다.
    - `SPOTV`계정만이 다른 `provider_type`을 연결 하고 있을 수 있으며 이는 `provider_type`당 1개 씩만을 보유 가능 합니다.
- account\_user : Account와 User 간의 연결을 의미 합니다.
    - `user_info`, `account_provider`가 없다면 해당 table 또한 없습니다.

> 2개의 매핑 Table은 설계상 2개가 쌍으로 존재하여 대상의 'Target'이 결국에 같은 'User\_info'를 바라봐야 합니다.

### Controller (API별 인증 및 Key)

- Account : `id_token.subject`의 값을 `key`로 사용 합니다.
- User : `id_token.userinfo_id`의 값을 `key`로 사용 합니다.
- Signin : 비 회원 API에 대하여 처리 합니다.

### Enums (Error Codes)

정책 기 설정에 따라 아래와 같은 예외 처리가 발생 할 수 있습니다. 이는 단순한 기본적 `Error`와 별도로 정책에 따른 `Error_Code`입니다.

`error_hint`에 정의된 내용에 따라 event의 분기 또는 guide가 필요합니다.

Sample

```json
    {
    "status_code": 400,
    "error": "Bad Request",
    "error_description": "is exist ci item",
    "error_hint": "SPI00006"
    }
```

List

```java
    // defalut Error Code
    API_SERVER_ERROR    ("SPI00001", HttpStatus.INTERNAL_SERVER_ERROR, null),
    UNAUTHORIZED        ("SPI00002", HttpStatus.UNAUTHORIZED, null),
    NOT_FOUND           ("SPI00003", HttpStatus.NOT_FOUND, null),
    BAD_REQEUST         ("SPI00005", HttpStatus.BAD_REQUEST, null),
            
            
    ITEM_NOT_FOUND      ("SPI00004", HttpStatus.NOT_FOUND, "item is nor found"),
    EXIST_CI            ("SPI00006", HttpStatus.BAD_REQUEST, "is exist ci item"),
    PASS_MISS_MATCH     ("SPI00007", HttpStatus.BAD_REQUEST, "Password miss matches"),
    MCASH_EXPIRE        ("SPI00008", HttpStatus.BAD_REQUEST, "Not Find Trace ID"),
    MCASH_UN_SIGN       ("SPI00009", HttpStatus.BAD_REQUEST, "Not a valid signature."),
    ACCOUNT_LINKED_EXIST("SPI00010", HttpStatus.BAD_REQUEST, "account already has an account linked to it."),
    PROVIDER_LINK_EXIST ("SPI00011", HttpStatus.BAD_REQUEST, "The corresponding Provider Type is already connected to the representative account."),
    INCORRECT_PROVIDER  ("SPI00012", HttpStatus.BAD_REQUEST, "'provider type' is incorrect."),
    USER_INFO_EXIST     ("SPI00013", HttpStatus.BAD_REQUEST, "account_user is exists"),
    NOT_HAVE_REP        ("SPI00014", HttpStatus.BAD_REQUEST, "The combined account does not have a representative account type \"SPOTV\"."),
    UNABLE_SNS_TYPE     ("SPI00014", HttpStatus.BAD_REQUEST, "Unable to connect \"provider_type\".");
```