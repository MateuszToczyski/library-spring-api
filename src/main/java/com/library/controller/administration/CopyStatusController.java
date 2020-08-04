package com.library.controller.administration;

import com.library.domain.CopyStatusDto;
import com.library.exception.CopyStatusNotFoundException;
import com.library.mapping.CopyStatusMapper;
import com.library.service.CopyStatusDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("administration/statuses")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CopyStatusController {

    private final CopyStatusDbService dbService;
    private final CopyStatusMapper mapper;

    @RequestMapping(method = RequestMethod.GET)
    public List<CopyStatusDto> getStatuses() {
        return mapper.mapToCopyStatusDtoList(dbService.getAllStatuses());
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public CopyStatusDto getStatus(@PathVariable Long id) {
        return mapper.mapToCopyStatusDto(dbService.getStatus(id).orElseThrow(CopyStatusNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = APPLICATION_JSON_VALUE)
    public CopyStatusDto createStatus(@RequestBody CopyStatusDto statusDto) {
        return mapper.mapToCopyStatusDto(dbService.saveStatus(mapper.mapToCopyStatus(statusDto)));
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = APPLICATION_JSON_VALUE)
    public CopyStatusDto updateStatus(@RequestBody CopyStatusDto statusDto) {
        return mapper.mapToCopyStatusDto(dbService.saveStatus(mapper.mapToCopyStatus(statusDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void deleteStatus(@PathVariable Long id) {
        dbService.deleteStatus(id);
    }
}
