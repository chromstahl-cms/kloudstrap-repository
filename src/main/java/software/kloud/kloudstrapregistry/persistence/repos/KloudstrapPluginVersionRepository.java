package software.kloud.kloudstrapregistry.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import software.kloud.kloudstrapregistry.persistence.entities.KloudstrapPlugin;
import software.kloud.kloudstrapregistry.persistence.entities.KloudstrapPluginVersion;

import java.util.Optional;

public interface KloudstrapPluginVersionRepository extends JpaRepository<KloudstrapPluginVersion, Integer> {
    Optional<KloudstrapPluginVersion> findByKloudstrapPluginAndVersion(KloudstrapPlugin plugin, String version);
}
