package Validation.demo.response;

import lombok.Data;
import lombok.AllArgsConstructor;
@Data
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private boolean success;
}
