package com.rock.uber.rest;

import com.rock.uber.domain.Location;
import com.rock.uber.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FleetBulkUploadRestController {
    private LocationService locationService;

    @Autowired
    public FleetBulkUploadRestController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/fleet", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Location> locations) {
        this.locationService.saveCarLocations(locations);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.POST)
    public void purge() {
        this.locationService.deleteAll();
    }

    @RequestMapping(value = "/fleet/{movementType}", method = RequestMethod.GET)
    public Page<Location> findByMovementType(@PathVariable String movementType, @RequestParam("page") int page, @RequestParam("size") int size) {
        return this.locationService.findByVehicleMovementType(movementType, new PageRequest(page, size));
    }

    @RequestMapping(value = "/fleet/vin/{vin}", method = RequestMethod.GET)
    public Page<Location> findByVinNumber(@PathVariable String vin, @RequestParam("page") int page, @RequestParam("size") int size) {
        return this.locationService.findByVin(vin, new PageRequest(page, size));
    }
}
