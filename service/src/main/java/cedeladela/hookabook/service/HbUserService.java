package cedeladela.hookabook.service;

import cedeladela.hookabook.entity.HbUser;
import cedeladela.hookabook.repository.HbUserRepository;
import exception.hbuser.HbUserAndPasswordDoNotMatchException;
import exception.hbuser.HbUserExistsException;
import exception.hbuser.HbUserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HbUserService {

    private final HbUserRepository hbUserRepository;

    @Autowired
    public HbUserService(HbUserRepository hbUserRepository) {
        this.hbUserRepository = hbUserRepository;
    }

    public List<HbUser> getAll() {
        return (List<HbUser>) hbUserRepository.findAll();
    }

    public HbUser getById(Long id) {
        Optional<HbUser> userOptional = hbUserRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new HbUserNotFoundException(id);
        }
    }

    public HbUser create(HbUser hbUser) {
        return hbUserRepository.save(hbUser);
    }

    public HbUser update(HbUser hbUser) {
        Optional<HbUser> existingUserOptional = hbUserRepository.findById(hbUser.getId());
        if (existingUserOptional.isPresent()) {
            HbUser existingUser = existingUserOptional.get();

            existingUser.setUsername(hbUser.getUsername());
            existingUser.setPassword(hbUser.getPassword());
            existingUser.setEmail(hbUser.getEmail());
            existingUser.setFirstName(hbUser.getFirstName());
            existingUser.setLastName(hbUser.getLastName());
            existingUser.setIsApproved(hbUser.getIsApproved());

            return hbUserRepository.save(existingUser);
        } else {
            throw new HbUserNotFoundException(hbUser.getId());
        }
    }

    public void delete(Long id) {
        Optional<HbUser> existingUserOptional = hbUserRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            hbUserRepository.deleteById(id);
        } else {
            throw new HbUserNotFoundException(id);
        }
    }

    public HbUser register(HbUser hbUser) {

        boolean usernameExists = hbUserRepository.existsByUsername(hbUser.getUsername());
        boolean emailExists = hbUserRepository.existsByEmail(hbUser.getEmail());

        if (usernameExists || emailExists) {
            throw new HbUserExistsException(usernameExists, emailExists);
        }

        hbUser.setIsApproved(false);

        return hbUserRepository.save(hbUser);
    }

    public HbUser login(String username, String password) {

        Optional<HbUser> userOptional = Optional.ofNullable(hbUserRepository.findByUsernameAndPassword(username, password));
        return userOptional.orElseThrow(HbUserAndPasswordDoNotMatchException::new);
    }

    public HbUser approveUser(Long id) {
        Optional<HbUser> existingUserOptional = hbUserRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            HbUser existingUser = existingUserOptional.get();
            existingUser.setIsApproved(true);
            return hbUserRepository.save(existingUser);
        } else {
            throw new HbUserNotFoundException(id);
        }
    }
}
