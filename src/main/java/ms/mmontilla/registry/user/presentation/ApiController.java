package ms.mmontilla.registry.user.presentation.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import ms.mmontilla.registry.user.presentation.Api;
import ms.mmontilla.registry.user.presentation.dto.Phone;
import ms.mmontilla.registry.user.presentation.dto.UserIn;
import ms.mmontilla.registry.user.presentation.dto.UserOut;
import ms.mmontilla.registry.user.domain.port.UserPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/presentation/v1/users")
public class ApiController implements Api {


    private UserService userService;

    @PostMapping
    @Override
    public ResponseEntity<UserOut> createUser(@Parameter(in = ParameterIn.DEFAULT, description = "",
                                                         required=true,
                                                         schema=@Schema()) @Valid @RequestBody UserIn body) {
        UserOut user = userService.createUser(UserIn);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
