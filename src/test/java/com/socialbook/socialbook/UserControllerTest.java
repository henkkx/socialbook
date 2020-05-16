package com.socialbook.socialbook;

import com.socialbook.socialbook.shared.GenericResponse;
import com.socialbook.socialbook.user.User;
import com.socialbook.socialbook.user.UserRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String API_1_USERS = "/api/1.0/users";

    @Autowired
    TestRestTemplate testRestTemplate;

    @Autowired
    UserRepository userRepository;

    private User createValidUser(){
        User user = new User();
        user.setUsername("test-user");
        user.setDisplayName("test-display");
        user.setPassword("test-password");
        return user;
    }

    @Before
    public void cleanup(){
        userRepository.deleteAll();
    }

    @Test
    void postUser_whenUserIsValid_receiveOk() {
        User user = createValidUser();

        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_USERS, user, Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void postUser_whenUserIsValid_receiveSuccessMessage() {
        User user = createValidUser();

        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_USERS, user, GenericResponse.class);
        assertThat(response.getBody().getMessage()).isNotNull();
    }

    @Test
    void postUser_whenUserIsValid_userSavedToDatabase() {
        User user = createValidUser();
        testRestTemplate.postForEntity(API_1_USERS, user, Object.class);
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    void postUser_whenUserIsValid_passwordIsHashedInDatabase(){
        User user = createValidUser();
        testRestTemplate.postForEntity(API_1_USERS, user, Object.class);
        User userInDB = userRepository.findAll().get(0);
        assertThat(userInDB.getPassword()).isNotEqualTo(user.getPassword());
    }

}
