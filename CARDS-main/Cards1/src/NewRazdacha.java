import java.util.*;

public class NewRazdacha {

        public static final String RED = "\033[1;91m";   // RED
        public static final String GREEN = "\033[1;92m"; // GREEN
        public static final String YELLOW = "\033[1;93m";// YELLOW
        public static final String BLUE = "\033[1;94m";  // BLUE
        public static final String PURPLE = "\033[1;95m";// PURPLE
        public static final String CYAN = "\033[1;96m";  // CYAN

        public static final String RESET = "\033[0m";  // Text Reset
        public static final String ExpError = "\033[43m"+"\033[1;30m";

        private static ArrayList<String> MASSKOLODA;
        private static ArrayList<ArrayList<String>> PLAYERS;




    public static void OutWinthNum(ArrayList Mass){
        for (int i = 0; i <Mass.size() ; i++) {
            System.out.print(" "+(i+1)+"-"+Mass.get(i));

        }
    }

        public static void PickNewCard(ArrayList PLAYER,ArrayList Masscoloda,int playercardnumber){

            PLAYER.add(Masscoloda.get(0)); Masscoloda.remove(0);
            System.out.println("New Card Picked!"+(playercardnumber+1)+". "+Masscoloda.size());
        }

        public static String PlayerTurn(ArrayList PLAYER,String CardOnTable,ArrayList Masscoloda){
            boolean CanBeat = false;
            boolean CardAlreadyPicked = false;
            while (!CanBeat){
                System.out.println(CardOnTable);
                OutWinthNum(PLAYER);
                Scanner in = new Scanner(System.in);
                String input = in.nextLine();
                if(input.equalsIgnoreCase("P")&!CardAlreadyPicked){int playercardnumber=0;playercardnumber=PLAYER.size();
                    System.out.println(playercardnumber);
                    PickNewCard(PLAYER,Masscoloda,playercardnumber);CardAlreadyPicked=true;continue;}
                else if (input.equalsIgnoreCase("P")&CardAlreadyPicked) {System.out.println(ExpError+"Card is Already Picked"+RESET);}
                else if (input.equalsIgnoreCase("S")&!CardAlreadyPicked){System.out.println(ExpError+"You Cant Skip! Make your move or pick card!"+RESET);}
                else if (input.equalsIgnoreCase("S")&CardAlreadyPicked){System.out.println("Turn Skipped!"+"\n"+CardOnTable);break;}
                else {
                    int InputCardNum;
                    int playercardnumber=0;
                    try {
                        InputCardNum = Integer.parseInt(input)-1;
                    } catch (NumberFormatException e) {
                        System.out.println(ExpError+"INVALID INPUT!!!! SUKA BLAT!"+RESET);
                        continue;
                    }
                    if(InputCardNum == -1){InputCardNum++;}
                    try{
                    CanBeat=CanIAttack((String) PLAYER.get(InputCardNum),CardOnTable);}
                    catch (IndexOutOfBoundsException e){
                        System.out.println(ExpError+"INVALID NUMBER! YOU HAW ONLY "+PLAYER.size()+" CARDS, NOT "+(InputCardNum+1)+"!"+RESET);
                    }

                    if(CanBeat){CardOnTable = (String) PLAYER.get(InputCardNum);
                        PLAYER.remove(InputCardNum);

                        playercardnumber=PLAYERS.size();System.out.println(playercardnumber);
                        System.out.println(CardOnTable);}

                    else {System.out.println(ExpError+"You Cant Beat This!"+RESET+"\n"+RESET+CardOnTable+RESET);}}}

            return CardOnTable;
        }


        public static boolean CanIAttack (String AttackCard,String DefendCard){
            if(DefendCard == null)
            {return true;}
            else{
                String[] AttackCardSplit = AttackCard.split(" ");
                String[] DefendCardSplit = DefendCard.split(" ");
             //   System.out.println();
             //   System.out.println(AttackCard);
             //   System.out.println(DefendCard);
             //   System.out.println(Arrays.toString(AttackCardSplit));
             //   System.out.println(Arrays.toString(DefendCardSplit));
             //   System.out.println();
                return (AttackCardSplit[0].equals(DefendCardSplit[0])
                        ||AttackCardSplit[1].equals(DefendCardSplit[1])
                        ||AttackCardSplit[2].equals(DefendCardSplit[2]));
            }
        }

/*
это что за хуйня я не понимаю
        public static String[][] getColoda(){
            return new String[][] {MASSKOLODA, Player1, Player2, Player3, Player4,Player5,Player6};
        }



 */

        public void AddPlayer(){
            PLAYERS.add(new ArrayList<>());
            ArrayList<String> Player = PLAYERS.get(PLAYERS.size()-1);
            for (int i = 0; i < 6; i++) {
                Player.add(MASSKOLODA.get(0));
                MASSKOLODA.remove(0);
            }
        }

        public ArrayList<String> NewColoda(ArrayList<String> MASSKOLODA, String[] master, String[] typer, String[] figures){
            for (int mast = 0; mast < master.length; mast++) {

                for (int type = 0; type < typer.length; type++) {

                    for (int figure = 0; figure < figures.length; figure++) {
                        MASSKOLODA.add(String.format("%s %s %s",master[mast],typer[type],figures[figure])); System.out.printf("%s%s.%s  ",master[mast],typer[type],figures[figure]);
                    }
                    System.out.println(" ");
                }
                System.out.println(" ");
            }
            return MASSKOLODA;
        }

        public NewRazdacha() {
            MASSKOLODA = new ArrayList<>();

            String[] master= new String[] {RED,YELLOW,GREEN,BLUE,PURPLE,CYAN};
            String[] typer= new String[]{"1","2","3","4","5","6"};
            String[] figures= new String[]{"●"+RESET,"■"+RESET,"▲"+RESET,"⬡"+RESET,"n\b"+RESET,"〤"+RESET};//●■▲⬢〤

            MASSKOLODA = NewColoda(MASSKOLODA, master, typer, figures);
            Collections.shuffle(MASSKOLODA);

            int Cycle =0;
            for (Cycle=0; Cycle <MASSKOLODA.size(); Cycle++) {
                System.out.print(MASSKOLODA.get(Cycle)+"\t");
                if ((Cycle%6==0)&(Cycle!=0)){ System.out.print("\n");}
            }
            PLAYERS = new ArrayList<ArrayList<String>>();

            // Создание игроков
            System.out.println(String.valueOf(MASSKOLODA.size()));
            for (int i = 0; i < 6; i++) {
                AddPlayer();
            }
            System.out.println(String.valueOf(MASSKOLODA.size()));

            System.out.println("\nEnd Shuffle");

            for (int i = 0; i < PLAYERS.size(); i++) {
                System.out.print("Player"+(i+1));
                for (String s : PLAYERS.get(i)) {
                    System.out.print(s + " ");
                }
                System.out.println();}

            //START!
            System.out.println("GAME STARTED!\n".repeat(3));

            int playernum = 0;
            Random r =new Random();
            playernum = r.nextInt(PLAYERS.size());
            System.out.println("Player "+(playernum+1)+" Make Turn First");
            boolean GameEnd=false;
            String CardOnTable = null;
            //игровой процесс тут V V V
            while(!GameEnd){
                if(playernum == 0) {System.out.println("Player 1 Turn ");CardOnTable=PlayerTurn(PLAYERS.get(0),CardOnTable,MASSKOLODA);if(PLAYERS.get(0).size()==0){GameEnd=true;System.out.println("Player 1 Wins!");break;}}
                if(playernum == 1) {System.out.println("Player 2 Turn ");CardOnTable=PlayerTurn(PLAYERS.get(1),CardOnTable,MASSKOLODA);if(PLAYERS.get(1).size()==0){GameEnd=true;System.out.println("Player 2 Wins!");break;}}
                if(playernum == 2) {System.out.println("Player 3 Turn ");CardOnTable=PlayerTurn(PLAYERS.get(2),CardOnTable,MASSKOLODA);if(PLAYERS.get(2).size()==0){GameEnd=true;System.out.println("Player 3 Wins!");break;}}
                if(playernum == 3) {System.out.println("Player 4 Turn ");CardOnTable=PlayerTurn(PLAYERS.get(3),CardOnTable,MASSKOLODA);if(PLAYERS.get(3).size()==0){GameEnd=true;System.out.println("Player 4 Wins!");break;}}
                if(playernum == 4) {System.out.println("Player 5 Turn ");CardOnTable=PlayerTurn(PLAYERS.get(4),CardOnTable,MASSKOLODA);if(PLAYERS.get(4).size()==0){GameEnd=true;System.out.println("Player 5 Wins!");break;}}
                if(playernum == 5) {System.out.println("Player 6 Turn ");CardOnTable=PlayerTurn(PLAYERS.get(5),CardOnTable,MASSKOLODA);if(PLAYERS.get(5).size()==0){GameEnd=true;System.out.println("Player 6 Wins!");break;}}
                playernum++;
                if (playernum ==(PLAYERS.size()+1)){playernum = 0;}}





        }
    }

