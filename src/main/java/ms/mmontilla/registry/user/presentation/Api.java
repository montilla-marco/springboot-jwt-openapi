package ms.mmontilla.registry.user.presentation;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Validated
public interface Api {

    @Operation(summary = "Create a UserVo",
            description = "Lets create an user post a entry in users registry",
            tags = {})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "OK user created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserOut.class)
                    )
            ),
            @ApiResponse(responseCode = "400",
                    description = "Invalid query Params",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            ),
            @ApiResponse(responseCode = "404",
                    description = "Service Not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            ),
            @ApiResponse(responseCode = "409",
                    description = "UserVo Already Exists",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            ),
            @ApiResponse(responseCode = "500",
                    description = "Exception Error",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)
                    )
            )})
    ResponseEntity<UserOut> createUser(@Parameter(in = ParameterIn.DEFAULT,
            description = "",
            required = true, schema = @Schema()) @Valid @RequestBody UserIn body);

}
