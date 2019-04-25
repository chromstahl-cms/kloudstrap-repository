package software.kloud.kloudstrapregistry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import software.kloud.kloudstrapregistry.exception.PluginNotFoundException;
import software.kloud.kloudstrapregistry.persistence.entities.KloudstrapPluginVersion;
import software.kloud.kloudstrapregistry.persistence.repos.KloudstrapPluginRepository;
import software.kloud.kloudstrapregistry.persistence.repos.KloudstrapPluginVersionRepository;

import java.util.Optional;

@Controller
@RequestMapping("/download")
public class DownloadController {
    private final KloudstrapPluginVersionRepository repository;
    private final KloudstrapPluginRepository pluginRepository;


    @Autowired
    public DownloadController(KloudstrapPluginVersionRepository repository, KloudstrapPluginRepository pluginRepository) {
        this.repository = repository;
        this.pluginRepository = pluginRepository;
    }

    @GetMapping(value = "/{name}/{version}", produces = "application/gzip")
    public ResponseEntity<FileSystemResource> getPluginFile(@PathVariable String name, @PathVariable String version) throws PluginNotFoundException {
        var plugin = pluginRepository.findByName(name).orElseThrow(PluginNotFoundException::new);
        Optional<KloudstrapPluginVersion> pluginOptional = repository.findByKloudstrapPluginAndVersion(plugin, version);
        if (pluginOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new FileSystemResource(pluginOptional.get().getFile()));
    }
}
