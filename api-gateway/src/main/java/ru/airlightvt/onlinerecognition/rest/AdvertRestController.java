package ru.airlightvt.onlinerecognition.rest;

import com.google.common.collect.Sets;
import ru.airlightvt.onlinerecognition.entity.Advert;
import ru.airlightvt.onlinerecognition.service.impl.AdvertService;
import ru.airlightvt.onlinerecognition.shared.Constants;
import ru.airlightvt.onlinerecognition.shared.dto.AdvertDto;
import ru.airlightvt.onlinerecognition.shared.trasformer.AdvertConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = Constants.API_ENDPOINT)
@CrossOrigin
public class AdvertRestController {
    private final AdvertService advertsService;

    @Autowired
    public AdvertRestController(AdvertService advertsService)
    {
        this.advertsService = advertsService;
    }

    @RequestMapping(value = "/adverts", method = RequestMethod.GET)
    public Collection<AdvertDto> getAllAdverts(@RequestParam(value = "start", defaultValue = "0") Long start,
                                               @RequestParam(value = "size", defaultValue = "20") Integer size) {
        Set<AdvertDto> result = Sets.newHashSet();
        advertsService.getAdverts(start, size).forEach(it -> result.add(AdvertConverter.convert(it)));
        return result;
    }

    @RequestMapping(value = "/adverts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public List<AdvertDto> saveAdverts(@RequestBody(required = true) List<AdvertDto> advertDtos) {
        List<Advert> adverts = advertDtos.stream().map(AdvertConverter::convert).collect(Collectors.toList());
        return advertsService.saveAdverts(adverts).stream().map(AdvertConverter::convert).collect(Collectors.toList());
    }

    @RequestMapping(value = "/adverts/{userId}", method = RequestMethod.GET)
    public Collection<AdvertDto> getAdvertsByAuthor(@PathVariable long userId,
                                                    @RequestParam(value = "start", defaultValue = "0") Long start,
                                                    @RequestParam(value = "size", defaultValue = "20") Integer size)
    {
        Set<AdvertDto> result = Sets.newHashSet();
        advertsService.getAdverts(start, size).forEach(it -> result.add(AdvertConverter.convert(it)));
        return result;
    }

    @RequestMapping(value = "/advert", method = RequestMethod.GET)
    public AdvertDto getAdvertById(@RequestParam(value = "id") long id) {
        Advert advert = advertsService.getAdvertById(id);
        return AdvertConverter.convert(advert);
    }

    @RequestMapping(value = "/advert", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public AdvertDto saveAdvert(@RequestBody(required = true) AdvertDto advertDto) {
        Advert advert = advertsService.saveOrUpdateAdvert(AdvertConverter.convert(advertDto));
        return AdvertConverter.convert(advert);
    }

    @RequestMapping(value = "/advert", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public AdvertDto updateAdvert(@RequestBody(required = true) AdvertDto advertDto) {
        Advert advert = advertsService.saveOrUpdateAdvert(AdvertConverter.convert(advertDto));
        return AdvertConverter.convert(advert);
    }

    @RequestMapping(value = "/advert", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteAdvert(@RequestParam(value = "id") long id) {
        advertsService.deleteAdvert(id);
    }
}
