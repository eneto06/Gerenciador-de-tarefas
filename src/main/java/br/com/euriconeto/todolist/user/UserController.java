package br.com.euriconeto.todolist.user;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity Create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUserName(userModel.getUserName());
        if(user != null) {
            System.out.println("Usuário já existe!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("usuário já existe");
        }

        var passwordHashered = BCrypt.withDefaults().
                hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashered);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.OK).body(userCreated);

    }
}
