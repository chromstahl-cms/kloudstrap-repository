package software.kloud.kloudstrapregistry.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import software.kloud.ChromPluginSDK.ChromStorage;
import software.kloud.kloudstrapregistry.persistence.entities.KloudstrapPlugin;
import software.kloud.kloudstrapregistry.persistence.entities.KloudstrapPluginVersion;
import software.kloud.kloudstrapregistry.persistence.repos.KloudstrapPluginRepository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/publish")
public class PublishController {

    private final Logger logger = LoggerFactory.getLogger(PublishController.class);
    private final KloudstrapPluginRepository repository;
    private final File pluginDir;
    @Autowired
    public PublishController(KloudstrapPluginRepository repository, ChromStorage storage) {
        pluginDir = new File(storage.getRoot(), "kloudstrapplugins");

        if (!pluginDir.isDirectory()) {
            if (!pluginDir.mkdirs()) {
                logger.error("Could not create base plugin dir, check write permissions");
            }
        }

        this.repository = repository;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<String> publishPlugin(@RequestParam("file") MultipartFile file, @RequestParam("name") String name,
                                                @RequestParam String version
    ) throws IOException {
        var plugin = this.repository.findByName(name)
                .orElse(new KloudstrapPlugin(name, version, new ArrayList<>()));

        if (plugin.getVersionList().stream().anyMatch(it -> it.getVersion().equals(version))) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(String.format("Version %s already present", version));
        }
        var pluginFile = new File(pluginDir, name + version + ".tar.gz");
        var pluginVersion = new KloudstrapPluginVersion();
        pluginVersion.setFile(pluginFile);
        pluginVersion.setVersion(version);
        pluginVersion.setKloudstrapPlugin(plugin);
        plugin.getVersionList().add(pluginVersion);
        file.transferTo(pluginFile);

        repository.save(plugin);

        return ResponseEntity.ok("success");
    }
}
