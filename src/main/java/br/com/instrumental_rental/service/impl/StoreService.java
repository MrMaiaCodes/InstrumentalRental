package br.com.instrumental_rental.service.impl;

import br.com.instrumental_rental.exceptions.StoreNotFoundException;
import br.com.instrumental_rental.repository.entities.Attendant;
import br.com.instrumental_rental.repository.entities.Customer;
import br.com.instrumental_rental.repository.entities.Instrument;
import br.com.instrumental_rental.repository.entities.Store;
import br.com.instrumental_rental.repository.interfaces.IStoreRepository;
import br.com.instrumental_rental.service.interfaces.IAttendantService;
import br.com.instrumental_rental.service.interfaces.ICustomerService;
import br.com.instrumental_rental.service.interfaces.IInstrumentService;
import br.com.instrumental_rental.service.interfaces.IStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StoreService implements IStoreService {


    IStoreRepository storeRepository;

    ICustomerService customerService;
    IAttendantService attendantService;
    IInstrumentService instrumentService;

    @Autowired
    private StoreService(IStoreRepository storeRepository, ICustomerService customerService,
                         IAttendantService attendantService, IInstrumentService instrumentService) {
        this.storeRepository = storeRepository;
        this.customerService = customerService;
        this.attendantService = attendantService;
        this.instrumentService = instrumentService;
    }

    @Override
    public List<Store> saveFirstTime(List<Store> storeList) throws StoreNotFoundException {
        if (storeList.isEmpty()) throw new StoreNotFoundException("S01", "Store not found");
        else {
            List<Store> savedStoreList = new ArrayList<>();
            for (Store store : storeList) {
                storeRepository.save(store);
                savedStoreList.add(store);
            }
            return savedStoreList;
        }

    }

    @Override
    public Store save(Store store) throws StoreNotFoundException {
        if (store == null) throw new StoreNotFoundException("S01", "Store not found");
        return storeRepository.save(store);
    }

    @Override
    public Store findById(Long storeId) throws StoreNotFoundException {
        Store storeFound = storeRepository.findById(storeId).orElseThrow(() ->
                new StoreNotFoundException("S01", "Store not found")
        );
        return storeFound;
    }

    @Override
    public Store update(Store store) throws StoreNotFoundException {
        Store storeFound = findById(store.getStoreId());
        storeFound.setName(store.getName());
        storeFound.setAttendants(store.getAttendants());
        storeFound.setCustomers(store.getCustomers());
        storeFound.setInstruments(store.getInstruments());
        storeFound.setTheAddress(store.getTheAddress());
        storeFound.setContacts(store.getContacts());
        save(storeFound);
        return storeFound;
    }

    /*
    @Override
    public void delete(Long rentalId) throws RentalNotFoundException {
        var rentalToDelete = findById(rentalId);
        rentalRepositoryAttribute.delete(rentalToDelete);
    }
     */
    @Override
    public void delete(Long storeId) throws StoreNotFoundException {
        var storeToDelete = findById(storeId);
        storeRepository.delete(storeToDelete);
    }

    @Override
    public List<Store> listAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findByName(String storeName) throws StoreNotFoundException {
        var storeFound = storeRepository.findStoreByName(storeName);
        if (storeFound == null) {
            throw new StoreNotFoundException("S01", "Store not found");
        } else {
            return storeFound;
        }
    }
}
