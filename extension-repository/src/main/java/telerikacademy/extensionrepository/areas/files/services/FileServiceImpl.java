package telerikacademy.extensionrepository.areas.files.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telerikacademy.extensionrepository.areas.files.data.FileRepository;
import telerikacademy.extensionrepository.areas.files.services.base.FileService;

@Service
public class FileServiceImpl implements FileService {
    private FileRepository fileRepository;

    @Autowired
    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void deleteFile(long id) {
        fileRepository.deleteById(id);
    }
}
