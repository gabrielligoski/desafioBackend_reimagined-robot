package com.example.desafiobackend;

import java.sql.Timestamp;
import java.util.Date;

import com.example.desafiobackend.model.Event;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EventsTests {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getEvent() throws Exception {
        this.mockMvc.perform(get("/api/event/1")).andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string("{\"id\":1,\"eventType\":\"type_A_event\",\"eventDate\":\"2022-02-07T23:08:44.000+00:00\"}"));
    }

    @Test
    public void postEvent() throws Exception {
        this.mockMvc.perform(post("/api/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Event(0L, "new Event", new Timestamp(new Date().getTime())))))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Evento criado com sucesso!"));
    }

    @Test
    public void putEvent() throws Exception {
        this.mockMvc.perform(put("/api/event/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new Event(0L, "new Event", new Timestamp(new Date().getTime())))))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Evento atualizado com sucesso!"));
    }

    @Test
    public void getAllEvents() throws Exception {
        this.mockMvc.perform(get("/api/events"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteEvent() throws Exception {
        this.mockMvc.perform(delete("/api/event/3"))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Evento deletado com sucesso!"));
    }

}
