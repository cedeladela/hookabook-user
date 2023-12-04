package cedeladela.hookabook;

import cedeladela.hookabook.entity.HbUser;
import cedeladela.hookabook.repository.HbUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final HbUserRepository hbUserRepository;

    @Autowired
    public DataLoader(HbUserRepository hbUserRepository) {
        this.hbUserRepository = hbUserRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        // Clear existing data in the database
        hbUserRepository.deleteAll();

        // Insert mock data
        insertMockUserData();
    }

    private void insertMockUserData() {
        List<HbUser> users = Arrays.asList(
                new HbUser(null, "test", "test", "test@hookabook.com", "John", "Doe", false),
                new HbUser(null, "john_doe", "password123", "john@hookabook.com", "John", "Doe", false),
                new HbUser(null, "jane_doe", "password456", "jane@hookabook.com", "Jane", "Doe", false),
                new HbUser(null, "bob_smith", "password789", "bob@hookabook.com", "Bob", "Smith", false)
        );

        // Save all users in a single call
        hbUserRepository.saveAll(users);
    }

}
