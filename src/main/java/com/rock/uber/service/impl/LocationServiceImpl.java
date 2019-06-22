package com.rock.uber.service.impl;

import com.rock.uber.domain.Location;
import com.rock.uber.domain.LocationRepository;
import com.rock.uber.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository ) {
        this.locationRepository = locationRepository;
    }
    public List<Location> saveCarLocations(List<Location> carLocations) {
        return locationRepository.save(carLocations);

    }
    public void deleteAll() {
        locationRepository.deleteAll();
    }

    public Page<Location> findByVehicleMovementType(String movementType, Pageable pageable) {
        return locationRepository
                .findByVehicleMovementType(Location.VehicleMovementType.valueOf(movementType), pageable);
    }

    public Page<Location> findByVin(String vin, Pageable pageable) {
        return null;
    }
}
