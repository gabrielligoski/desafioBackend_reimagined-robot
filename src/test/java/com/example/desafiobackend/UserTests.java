package com.example.desafiobackend;

import java.sql.Timestamp;
import java.util.Date;

import com.example.desafiobackend.model.User;
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
public class UserTests {

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
    public void getUser() throws Exception {
        this.mockMvc.perform(get("/api/user/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"nickname\":\"Gabe\",\"registration_date\":\"2022-02-08T19:22:26.000+00:00\"}"));
    }

    @Test
    public void postUser() throws Exception {
        this.mockMvc.perform(post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new User(0L, "new User", new Timestamp(new Date().getTime())))))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Úsuario criado com sucesso!"));
    }

    @Test
    public void putUser() throws Exception {
        this.mockMvc.perform(put("/api/user/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new User(0L, "new User", new Timestamp(new Date().getTime())))))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Úsuario atualizado com sucesso!"));
    }

    @Test
    public void getAllUsers() throws Exception {
        this.mockMvc.perform(get("/api/users"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void deleteUser() throws Exception {
        this.mockMvc.perform(delete("/api/user/3"))
                .andDo(print()).andExpect(status().is2xxSuccessful())
                .andExpect(content().string("Úsuario deletado com sucesso!"));
    }

}
