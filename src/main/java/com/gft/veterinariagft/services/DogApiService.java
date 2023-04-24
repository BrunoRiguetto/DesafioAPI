package com.gft.veterinariagft.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gft.veterinariagft.DTOs.vote.RegistroVoteDTO;
import com.gft.veterinariagft.DTOs.vote.VoteReponseGet;
import com.gft.veterinariagft.DTOs.vote.VoteSuccess;
import com.gft.veterinariagft.domain.dogapi.Breed;
import com.google.gson.Gson;

@Service
public class DogApiService {
    private final String apiKey = "4e4f2939-28f6-4da3-b688-36525e0e2091 ";

    public List<Breed> getAllBreeds() throws IOException {
        URL url = new URL("https://api.thedogapi.com/v1/breeds/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("x-api-key", apiKey);

        con.setConnectTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        return convertResponseToBreed(content.toString());
    }

    public List<VoteReponseGet> getAllVotes() throws IOException {
        URL url = new URL("https://api.thedogapi.com/v1/votes");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("x-api-key", apiKey);

        con.setConnectTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        return convertResponseToVoteResponseGet(content.toString());
    }

    public VoteSuccess postVote(RegistroVoteDTO dto) throws IOException {
        URL url = new URL("https://api.thedogapi.com/v1/votes");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        con.setRequestProperty("x-api-key", apiKey);

        String jsonInputString = json(dto);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        con.setConnectTimeout(5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        con.disconnect();

        return convertResponseToVoteSuccess(content.toString());

    }

    // ----------------------Utiliarios

    public List<Breed> convertResponseToBreed(String breedResponse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<Breed> list = Arrays.asList(mapper.readValue(breedResponse, Breed[].class));

        return list;
    }

    public List<VoteReponseGet> convertResponseToVoteResponseGet(String response) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<VoteReponseGet> list = Arrays.asList(mapper.readValue(response, VoteReponseGet[].class));

        return list;
    }

    public VoteSuccess convertResponseToVoteSuccess(String response) throws JsonProcessingException {

        Gson gson = new Gson();
        VoteSuccess voteSuccess = gson.fromJson(response, VoteSuccess.class);

        return voteSuccess;
    }

    public Breed buscarRacaPorId(int id) throws IOException {
        List<Breed> racas = getAllBreeds();

        for (Breed b : racas) {
            if (b.getId() == id)
                return b;
        }

        return null;
    }

    public String json(RegistroVoteDTO obj) {

        Gson gson = new Gson();

        String json = gson.toJson(obj);

        return json;

    }

}
