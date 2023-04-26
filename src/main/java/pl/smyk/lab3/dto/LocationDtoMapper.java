package pl.smyk.lab3.dto;

import pl.smyk.lab3.model.Location;

public class LocationDtoMapper {
    public LocationDTO map(Location location) {
        LocationDTO dto = new LocationDTO();
        dto.setId(location.getId());
        dto.setName(location.getName());
        dto.setDescription(location.getDescription());
        dto.setCodeId(location.getCode().getId());
        return dto;
    }
}
