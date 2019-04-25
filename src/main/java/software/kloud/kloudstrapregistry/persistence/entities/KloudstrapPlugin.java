package software.kloud.kloudstrapregistry.persistence.entities;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
public class KloudstrapPlugin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id",
            unique = true
    )
    private Integer id;
    @Column(unique = true)
    private String name;
    private String currentVersion;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<KloudstrapPluginVersion> versionList;

    public KloudstrapPlugin(String name, String currentVersion, List<KloudstrapPluginVersion> versionList) {
        this.name = name;
        this.currentVersion = currentVersion;
        this.versionList = versionList;
    }

    public KloudstrapPlugin() {
    }

    public List<KloudstrapPluginVersion> getVersionList() {
        return versionList;
    }

    public void setVersionList(List<KloudstrapPluginVersion> versionList) {
        this.versionList = versionList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }
}
