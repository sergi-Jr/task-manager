package hexlet.code.app.component;

import hexlet.code.app.role.repository.UserRoleRepository;
import hexlet.code.app.role.type.UserRoleType;
import hexlet.code.app.task.model.TaskStatus;
import hexlet.code.app.task.repository.TaskStatusRepository;
import hexlet.code.app.user.User;
import hexlet.code.app.user.dto.UserCreateDTO;
import hexlet.code.app.user.UserMapper;
import hexlet.code.app.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Profile("development")
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TaskStatusRepository statusRepository;

    @Autowired
    private final UserRoleRepository roleRepository;

    @Autowired
    private final UserMapper mapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initUser();
        initTaskStatuses(new String[]{"draft", "to_review", "to_be_fixed", "to_publish", "published"});
    }

    private void initUser() {
        UserCreateDTO data = new UserCreateDTO();
        data.setEmail("hexlet@example.com");
        data.setPassword("qwerty");
        User user = mapper.map(data);
        user.addUserRole(UserRoleType.ADMIN);
        userRepository.save(user);
    }

    private void initTaskStatuses(String[] defaults) {
        for (var str : defaults) {
            var status = new TaskStatus();
            status.setName(str);
            status.setSlug(str);
            statusRepository.save(status);
        }
    }
}
