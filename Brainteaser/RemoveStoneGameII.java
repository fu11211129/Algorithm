import java.util.*;

class RemoveStoneGameII {
  public static void main (String[] args) {
    int stones = 10;
    int m = 4;
    int winner = winnerUtil(stones, m);
    System.out.println(winner == 0? "win": "not-win");
  }

  static int winnerUtil(int stones, int m) {
    int player = 0;
    TreeSet<Integer> cards = new TreeSet<> (Collections.reverseOrder());
    //int sum = 0;
    for(int i=1; i<=m; ++i) {
      cards.add(i);
      //sum += i;
    }

    while(stones > 0) {
      int kk = 0;
      for(int k: cards) {
        cards.remove(k);
        if(cards.isEmpty() || stones <= 0) return player;

        if((stones - k) > cards.first()) {
            kk = k;
            break;
        }

        cards.add(k);
      }
      //cards.remove(kk);
      stones -= kk;
      if(cards.isEmpty() || stones <= 0) return player;
      player = 1 - player;
    }

    return player;
  }
}


/*
Your last C/C++ code is saved below:
// N stones
// pick the last stone to win

// Cards has number: 1 , 2 , 3 , ... , M

// player first select card, then pick k stones, k is the same number as that on card

// If the number on card is greater than the remaining stones, that player pick all stones

// Cards can only be chosen once

// both player will make their best effort to win

// bool canWin(int N, int M)


#include <iostream>
using namespace std;

int main() {
  int
  cout<<"Hello";
  return 0;
}



n <= m, player 1 win

n = m+1, player 2 win
n = m+2, player 2 win
n = m+m, player 2 win
n = m + m
 */
