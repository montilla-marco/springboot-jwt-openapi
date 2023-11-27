package ms.mmontilla.registry.user.presentation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.presentation.adapter.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/presentation/v1/users")
public class ApiController implements Api {

    private final UserService userService;

    public ApiController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Override
    public ResponseEntity<UserOut> createUser(@Parameter(in = ParameterIn.DEFAULT,
            description = "",
            required = true,
            schema = @Schema())
                                              @Valid @RequestBody UserIn body) {
        UserOut user = userService.createUser(body);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
