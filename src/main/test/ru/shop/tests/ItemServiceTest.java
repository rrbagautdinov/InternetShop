package ru.shop.tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.shop.entity.Item;
import ru.shop.exception.ItemNotFoundException;
import ru.shop.repository.ItemRepository;
import ru.shop.service.ItemService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = ItemService.class)
public class ItemServiceTest {
    @Autowired
    private ItemService itemService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    public void testFindItemById() throws ItemNotFoundException {
        Item itemMock = new Item();
        itemMock.setId(1L);
        itemMock.setName("Колбаска");
        itemMock.setPrice(100);

        Mockito
                .doReturn(Optional.of(itemMock))
                .when(itemRepository)
                .findById(1L);

        Item item = itemService.findItemById(1L);
        assertEquals("Колбаска", item.getName());
    }

    @Test
    public void testSaveItem() {
        Item itemMock = new Item();
        itemMock.setId(1L);
        itemMock.setName("Кокосик");
        itemMock.setPrice(100);

        Mockito
                .doReturn(Optional.of(itemMock))
                .when(itemRepository)
                .save(itemMock);

        assertEquals("Кокосик", itemMock.getName());
    }
}
