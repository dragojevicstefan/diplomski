package com.example.scooterRent.controller;

import com.example.scooterRent.dto.AddPicDTO;
import com.example.scooterRent.dto.ScooterDTO;
import com.example.scooterRent.dto.ScooterFilterDTO;
import com.example.scooterRent.model.Image;
import com.example.scooterRent.model.RentRequest;
import com.example.scooterRent.model.Scooter;
import com.example.scooterRent.service.RentRequestService;
import com.example.scooterRent.service.ScooterService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ScooterController {

    @Autowired
    private ScooterService scooterService;

    @Autowired
    private RentRequestService rentRequestService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/api/scooter/generate")
    public ResponseEntity<Scooter> generateScooter(@RequestBody ScooterDTO scooterDto) {
        System.out.println("usao u generisanje"+scooterDto.toString());
        Scooter s=scooterService.getScooter(scooterDto.getTitle());

        if(s==null){

            Scooter sc= new Scooter();
            sc.setActive(true);
            sc.setCapacity(Integer.parseInt(scooterDto.getCapacity()));
            sc.setDescription(scooterDto.getDescription());
            sc.setMaxRange(Integer.parseInt(scooterDto.getMaxRange()));
            sc.setMaxWeight(Integer.parseInt(scooterDto.getMaxWeight()));
            sc.setPrice(Integer.parseInt(scooterDto.getPrice()));
            sc.setSpeedLimit(Integer.parseInt(scooterDto.getSpeedLimit()));
            sc.setTitle(scooterDto.getTitle());

            scooterService.save(sc);
            System.out.println("New ad with name " + sc.getTitle() + "is added.");


            return new ResponseEntity<>(s, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(s,HttpStatus.BAD_REQUEST);


    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/api/scooter/addPic", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addPictures(@RequestBody AddPicDTO addPicDTO, @RequestParam(value = "title", required = true) String title) {
        System.out.println(addPicDTO.getFile());
        System.out.println(addPicDTO.getFileSource());
        System.out.println(title);
        Scooter s=scooterService.getScooter(title);


        Image img=new Image();
        img.setIdOglasa(s.getId());
        img.setFileSource(addPicDTO.getFileSource());
        try {
            FileOutputStream f = new FileOutputStream(new File("myObjects.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(img);
            o.close();
            f.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        }

        return "";
    }

    @PostMapping(value = "/api/scooter/getPic", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getPics(@RequestBody Long id) throws FileNotFoundException {
        Image pr1 = new Image();
        FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
        boolean cont = true;
        ArrayList<Image> imgs = new ArrayList<>();
        ArrayList<String> str = new ArrayList<>();
        while (cont) {
            try (ObjectInputStream oi = new ObjectInputStream(fi)) {
                Image pr2 = (Image) oi.readObject();
                if (pr2 != null) {
                    imgs.add(pr2);
                } else {
                    cont = false;
                }
            } catch (Exception e) {
                // System.out.println(e.printStackTrace());
            }
            for (Image i : imgs
            ) {
                if (i.getIdOglasa().equals(id)) {
                    return new ResponseEntity<>(i.getFileSource(), HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity<>(str, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/api/rent/allFilter", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Scooter>> rentFilter(@RequestBody ScooterFilterDTO addto)
    {
        DateTime startD = DateTime.parse(addto.getStartDate());
        DateTime endD = DateTime.parse(addto.getEndDate());
        List<Scooter> lista=scooterService.findAll();
        List<Scooter> pom=new ArrayList<>();



        List<RentRequest> rent_list = rentRequestService.findAll();


        if(rent_list.size() != 0) {
            for(RentRequest r : rent_list) {
                    Scooter c=r.getScooterForRent();
                            if(r.getReservedFrom().after(startD.toDate()) && r.getReservedTo().before(endD.toDate())) {
                                pom.add(c);
                            } else if (r.getReservedFrom().after(startD.toDate()) && r.getReservedTo().after(endD.toDate()) && r.getReservedFrom().before(endD.toDate())) {
                                pom.add(c);
                            } else if (r.getReservedFrom().before(startD.toDate()) && r.getReservedTo().before(endD.toDate()) && r.getReservedTo().after(startD.toDate())) {
                                pom.add(c);
                            }
            }
        }

        if(pom.size()!=0){
            for(Scooter p:pom){
                lista.remove(p);
            }
        }



        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

}
