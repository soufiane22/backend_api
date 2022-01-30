package ma.fstt.Clustuering.controlers;


import lombok.RequiredArgsConstructor;
import ma.fstt.Clustuering.entities.Client;
import ma.fstt.Clustuering.entities.Response;
import ma.fstt.Clustuering.reposotories.ClientRepository;
import ma.fstt.Clustuering.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    private final ClientService clientService;

    @CrossOrigin(origins = "*")
    @GetMapping("/list/{limit}")
    public ResponseEntity<Response> getClient(@PathVariable("limit") int limit) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("clients", clientService.list(limit)))
                        .message("clients retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getClientById(@PathVariable("id") String id) {
        System.out.println("get client by id ");
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("clients", clientService.get(id)))
                        .message("client retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/save")
    public ResponseEntity<Response> SaveClient(@RequestBody Client client) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("client",clientService.save(client)))
                        .message("client created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> DeleteClient(@PathVariable("id") String id) {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("client deleted",clientService.delete(id)))
                        .message("client is deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @GetMapping(path = "")
    public String test() {
        return "server is running";
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getall")
    public  ResponseEntity<Response> getAllClients() {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("clients", clientService.getALL()))
                        .message("clients retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getgender")
    public ResponseEntity<Response> getGender() {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("gender", clientService.getGender()))
                        .message("clients retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getstatistic")
    public ResponseEntity<Response> getStatistic() {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(clientService.getStatistic())
                        .message("clients retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/children")
    public ResponseEntity<Response> getChildren() {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(clientService.getChildren())
                        .message("clients retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/getclusters")
    public ResponseEntity<Response> getClusters() {

        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(clientService.getClusters())
                        .message("clients retrieve")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
