package pl.kepski.busdemo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.kepski.busdemo.entity.Ad;
import pl.kepski.busdemo.repository.AdRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {

    private final AdRepository adRepository;

    public AdService(AdRepository adRepository) {
        this.adRepository = adRepository;
    }

    public List<Ad> findAll() {
        return adRepository.findAll();
    }

    public Page<Ad> findPage(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1,6);
        return adRepository.findAll(pageable);
    }

    public Optional<Ad> findById(Long id) {
        return adRepository.findById(id);
    }

    public List<Ad> findByUserId(Long id) {
        return adRepository.findByUserId(id);
    }

    public void deleteById(Long id) {
        adRepository.deleteById(id);
    }
}
