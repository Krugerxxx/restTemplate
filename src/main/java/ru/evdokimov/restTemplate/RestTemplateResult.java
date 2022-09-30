package ru.evdokimov.restTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.evdokimov.restTemplate.models.User;

import java.util.Arrays;


@Service
public class RestTemplateResult {

    private final String URL = "http://94.198.50.185:7081/api/users";
    private final int ID = 3;
    private RestTemplate restTemplate;
    private HttpHeaders httpHeaders;
    private String result;
    private HttpEntity requestEntity;

    @Autowired
    public RestTemplateResult(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.httpHeaders = new HttpHeaders();
        this.result = "";
    }

    public String getResult() {
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URL, User[].class);
        httpHeaders.add("Cookie", responseEntity.getHeaders().getFirst("set-cookie"));

        User[] users = responseEntity.getBody();
        Arrays.stream(users).forEach(n -> System.out.println(n.getId() + " " + n.getAge() + " " + n.getName() + " " + n.getLastName()));

        User userPost;
        if (!Arrays.stream(users).anyMatch(n -> n.getId() == ID)) {
            userPost = new User();
            userPost.setId(Long.valueOf(3));
            userPost.setAge(Byte.valueOf((byte) 36));
            userPost.setName("James");
            userPost.setLastName("Brown");
        } else {
            userPost = Arrays.stream(users).filter(n -> n.getId() == ID)
                    .findFirst().get();
            userPost.setName("James");
            userPost.setLastName("Brown");
        }

        requestEntity = new HttpEntity<>(userPost, httpHeaders);
        ResponseEntity responseEntityForResult = restTemplate.exchange(URL, HttpMethod.POST, requestEntity, String.class);
        result += responseEntityForResult.getBody().toString();


        User userPut;
        if (!Arrays.stream(users).anyMatch(n -> n.getId() == ID)) {
            userPut = new User();
            userPut.setId(Long.valueOf(3));
            userPut.setAge(Byte.valueOf((byte) 36));
            userPut.setName("Thomas");
            userPut.setLastName("Shelby");
        } else {
            userPut = Arrays.stream(users).filter(n -> n.getId() == ID)
                    .findFirst().get();
            userPut.setName("Thomas");
            userPut.setLastName("Shelby");
        }

        requestEntity = new HttpEntity<>(userPut, httpHeaders);
        responseEntityForResult = restTemplate.exchange(URL, HttpMethod.PUT, requestEntity, String.class);
        result += responseEntityForResult.getBody().toString();

        requestEntity = new HttpEntity(httpHeaders);
        responseEntityForResult = restTemplate.exchange(URL + "/" + ID, HttpMethod.DELETE, requestEntity, String.class);
        result += responseEntityForResult.getBody().toString();

        return result;
    }

}
