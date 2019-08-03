package com.example.ajisaputrars.madesubmission2;

import com.example.ajisaputrars.madesubmission2.model.tvShow.TvShow;

import java.util.ArrayList;
import java.util.Arrays;

class TvShowData {
    private static ArrayList<String> getNames() {
        String[] names = {
                "Final Space",
                "JoJo no Kimyou na Bouken Ougon no Kaze",
                "Krypton",
                "Legacies",
                "Titans",
                "Station 19",
                "The Rookie",
                "Siren",
                "Chilling Adventures of Sabrina",
                "The Rain"
        };
        return new ArrayList<>(Arrays.asList(names));
    }

    private static ArrayList<String> getOverviews() {
        String[] overviews = {
                "An astronaut named Gary and his planet-destroying sidekick Mooncake embark on serialized journeys through space in order to unlock the mystery of “Final Space,” the last point in the universe, if it actually does exist.",
                "JoJo's Bizarre Adventure Part 5: Golden Wind, JoJo no Kimyou na Bouken Part 5: Ougon no Kaze, Le Bizzarre Avventure Di GioGio Parte 5: Vento Aureo",
                "Set two generations before the destruction of the legendary Man of Steel’s home planet, Krypton follows Superman’s grandfather — whose House of El was ostracized and shamed — as he fights to redeem his family’s honor and save his beloved world from chaos.",
                "In a place where young witches, vampires, and werewolves are nurtured to be their best selves in spite of their worst impulses, Klaus Mikaelson’s daughter, 17-year-old Hope Mikaelson, Alaric Saltzman’s twins, Lizzie and Josie Saltzman, among others, come of age into heroes and villains at The Salvatore School for the Young and Gifted",
                "A team of young superheroes led by Nightwing (formerly Batman's first Robin) form to combat evil and other perils.",
                "A group of heroic firefighters at Seattle Fire Station 19—from captain to newest recruit—risk their lives and hearts both in the line of duty and off the clock. These brave men and women are like family, literally and figuratively, and together they put their own lives in jeopardy as first responders to save the lives of others",
                "Starting over isn’t easy, especially for small-town guy John Nolan who, after a life-altering incident, is pursuing his dream of being an LAPD officer. As the force’s oldest rookie, he’s met with skepticism from some higher-ups who see him as just a walking midlife crisis.",
                "The coastal town of Bristol Cove is known for its legend of once being home to mermaids. When the arrival of a mysterious girl proves this folklore all too true, the battle between man and sea takes a very vicious turn as these predatory beings return to reclaim their right to the ocean.",
                "As her 16th birthday nears, Sabrina must choose between the witch world of her family and the human world of her friends. Based on the Archie comic.",
                "After a brutal virus wipes out most of the population, two young siblings embark on a perilous search for safety. A Scandinavian thriller series"
        };
        return new ArrayList<>(Arrays.asList(overviews));
    }

    private static ArrayList<String> getFirstAirDates() {
        String[] dates = {"19/10/2018",
                "17/10/2017",
                "22/03/2016",
                "12/12/2018",
                "12/02/2019",
                "13/05/2019",
                "22/07/2018",
                "12/11/2017",
                "16/03/2018",
                "15/11/2019"
        };
        return new ArrayList<>(Arrays.asList(dates));
    }

    private static ArrayList<Double> getVoteAverages() {
        double[] averages = {
                9.2,
                9.1,
                8.7,
                7.3,
                9.0,
                8.6,
                8.5,
                8.1,
                7.8,
                6.3
        };

        ArrayList<Double> list = new ArrayList<>();
        for (Double aData : averages) {
            list.add(aData);
        }
        return list;
    }

    private static ArrayList<Integer> getPosterPath() {
        Integer[] images = {
                R.drawable.tvposter1,
                R.drawable.tvposter2,
                R.drawable.tvposter3,
                R.drawable.tvposter4,
                R.drawable.tvposter5,
                R.drawable.tvposter6,
                R.drawable.tvposter7,
                R.drawable.tvposter8,
                R.drawable.tvposter9,
                R.drawable.tvposter10
        };

        return new ArrayList<>(Arrays.asList(images));
    }

    private static ArrayList<Integer> getBackDropPath() {
        Integer[] images = {
                R.drawable.tvposter1,
                R.drawable.tvposter2,
                R.drawable.tvposter3,
                R.drawable.tvposter4,
                R.drawable.tvposter5,
                R.drawable.tvposter6,
                R.drawable.tvposter7,
                R.drawable.tvposter8,
                R.drawable.tvposter9,
                R.drawable.tvposter10
        };
        return new ArrayList<>(Arrays.asList(images));
    }

    public static ArrayList<TvShow> getTvShowListData() {
        ArrayList<TvShow> data = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            TvShow tvShows = new TvShow();
            tvShows.setId(i);
            tvShows.setName(TvShowData.getNames().get(i));
            tvShows.setOverview(TvShowData.getOverviews().get(i));
            tvShows.setPoster_path(TvShowData.getPosterPath().get(i));
            tvShows.setFirst_air_date(TvShowData.getFirstAirDates().get(i));
            tvShows.setBackdrop_path(TvShowData.getBackDropPath().get(i));
            tvShows.setVote_average(TvShowData.getVoteAverages().get(i));

            data.add(tvShows);
        }
        return data;
    }
}
