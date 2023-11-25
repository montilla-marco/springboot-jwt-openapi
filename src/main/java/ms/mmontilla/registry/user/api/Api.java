package ms.mmontilla.registry.user.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;

import ms.mmontilla.registry.user.api.model.UserIn;
import ms.mmontilla.registry.user.api.model.UserOut;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Validated
public interface Api {

    @Operation(summary = "Create a User", description = "Lets create an user post a entry in users registry", tags={  })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                         description = "OK user created",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = UserOut.class)
                         )
            ),
            @ApiResponse(responseCode = "200",
                         description = "Unexpected Error",
                         content = @Content(mediaType = "application/json",
                                            schema = @Schema(implementation = Error.class))) })
    @RequestMapping(value = "/api/v1/users",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    ResponseEntity<UserOut> createUser(@Parameter(in = ParameterIn.DEFAULT,
                                                  description = "",
                                                  required=true, schema=@Schema()) @Valid @RequestBody UserIn body);

}
