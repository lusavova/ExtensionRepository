package telerikacademy.extensionrepository.models;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private long size;

    @Column(nullable = false)
    private String fileLocation;

    public File() {
    }

    public File(String fileName, String type, long size, String fileLocation) {
        setFileName(fileName);
        setType(type);
        setSize(size);
        setFileLocation(fileLocation);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    @Transient
    @Override
    public String toString() {
        return "Filename:" + fileName +
                "\nType:" + type +
                "\nSize:" + size +
                "\nLocation " + fileLocation;
    }
}