package software.kloud.kloudstrapregistry.persistence.entities;

import javax.persistence.*;
import java.io.File;

@Entity
public class KloudstrapPluginVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            unique = true
    )
    private Integer id;
    @ManyToOne
    private KloudstrapPlugin kloudstrapPlugin;
    private String version;
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public KloudstrapPlugin getKloudstrapPlugin() {
        return kloudstrapPlugin;
    }

    public void setKloudstrapPlugin(KloudstrapPlugin kloudstrapPlugin) {
        this.kloudstrapPlugin = kloudstrapPlugin;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        }

        if(!(obj instanceof KloudstrapPluginVersion)) {
            return false;
        }

        return ((KloudstrapPluginVersion) obj).getVersion().equals(this.version);
    }
}
