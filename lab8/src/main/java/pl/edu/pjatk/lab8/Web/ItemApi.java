package pl.edu.pjatk.lab8.Web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pl.edu.pjatk.lab8.Domain.Item;
import pl.edu.pjatk.lab8.Service.ItemManager;

@RestController
public class ItemApi{

    @Autowired
    ItemManager itemManager;

    
    @RequestMapping("/")
    public String index() {
        return "Spis przedmiotów :) ";
    }

    @RequestMapping(
            value = "/item",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<Item> getAllItems(@RequestParam(value = "name", defaultValue = "item") String name)throws SQLException {
        List<Item> itemsList = new ArrayList<Item>();
        itemsList = itemManager.getAllItems();
        System.out.println(itemsList);
        return itemsList;
    }


    @RequestMapping(
            value = "/item/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<Item> getOneNote(@PathVariable("id") int id) throws SQLException {
        Item item = itemManager.selectItem(id);
        if (item == null) {
            return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Item>(item, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/item/{id}",
            method = RequestMethod.DELETE
    )
    @ResponseBody
    public ResponseEntity<Item> deleteNote(@PathVariable("id") int id) throws SQLException{
        Item item = itemManager.selectItem(id);
        if(item == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        itemManager.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    @RequestMapping(
            value = "/item/{id}",
            method = RequestMethod.PUT
    )
    @ResponseBody
    public ResponseEntity<Item> editNote(@PathVariable("id") int id, @RequestBody Item item) throws SQLException{
        Item toEditNote = itemManager.selectItem(id);
        if(toEditNote == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        toEditNote.setName(item.getName());
        toEditNote.setDate(item.getDate());
        toEditNote.setAvailable(item.getAvailable());
        itemManager.updateItem(toEditNote);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(
            value = "item",
            method = RequestMethod.POST,
            produces = "application/json"

    )
    @ResponseBody
    public ResponseEntity<Void> addItem(@RequestBody Item item,UriComponentsBuilder ucBuilder) throws SQLException{
        itemManager.addItem(item);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
