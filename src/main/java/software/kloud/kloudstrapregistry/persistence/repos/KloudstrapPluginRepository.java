package software.kloud.kloudstrapregistry.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import software.kloud.kloudstrapregistry.persistence.entities.KloudstrapPlugin;

import java.util.Optional;

public interface KloudstrapPluginRepository extends JpaRepository<KloudstrapPlugin, Integer> {
    Optional<KloudstrapPlugin> findByName(String name);
}
