package software.kloud.kloudstrapregistry;

import org.springframework.stereotype.Component;
import software.kloud.ChromPluginSDK.NavBarEntity;
import software.kloud.ChromPluginSDK.NavBarLinkRegister;
import software.kloud.ChromPluginSDK.RoleService;

import java.util.List;

@Component
public class NavBarRegister implements NavBarLinkRegister {
    private final RoleService roleService;

    public NavBarRegister(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public List<NavBarEntity> register() {
        return List.of(new NavBarEntity("Upload new Plugin", "/upload", roleService.getAdminRole()));
    }
}
