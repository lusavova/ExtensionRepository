package telerikacademy.daredevil.extensionrepository.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import telerikacademy.daredevil.extensionrepository.models.GithubInfo;

public interface GithubInfoRepository extends JpaRepository<GithubInfo, Long> {
}