//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            int n = Integer.parseInt(read.readLine());
            Solution ob = new Solution();
            ArrayList<String> result = ob.NBitBinary(n);
            for(String value  : result){
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
// } Driver Code Ends




//User function Template for Java

class Solution {
    ArrayList<String> NBitBinary(int N) {
        ArrayList<String> r = new ArrayList<String>();
        int first = 1 << (N - 1);
        int last = first * 2;
     
    
        for (int i = last - 1; i >= first; --i)
        {
          int zero_cnt = 0;
          int one_cnt = 0;
          int t = i;
          int num_of_bits = 0;
     
          while (t > 0) 
          {
            if ((t & 1) != 0)
              one_cnt++;
            else
              zero_cnt++;
            num_of_bits++;
            t = t >> 1;
          }
     
      
          if (one_cnt >= zero_cnt)
          {
            boolean all_prefix_match = true;
            int msk = (1 << num_of_bits) - 2;
            int prefix_shift = 1;
            while (msk > 0) 
            {
     
              int prefix = (msk & i) >> prefix_shift;
              int prefix_one_cnt = 0;
              int prefix_zero_cnt = 0;
              while (prefix > 0)
              {
                if ((prefix & 1)!=0)
                  prefix_one_cnt++;
                else
                  prefix_zero_cnt++;
                prefix = prefix >> 1;
              }
              if (prefix_zero_cnt > prefix_one_cnt)
              {
                all_prefix_match = false;
                break;
              }
              prefix_shift++;
              msk = msk & (msk << 1);
            }
            if (all_prefix_match)
            {
              r.add(getBinaryRep(i, num_of_bits));
            }
          }
        }
        return r;
    }
     static String getBinaryRep(int N, int num_of_bits)
      {
        String r = "";
        num_of_bits--;
     
        while (num_of_bits >= 0) 
        {
          if ((N & (1 << num_of_bits))!=0)
            r += "1";
          else
            r += "0";
          num_of_bits--;
        }
        return r;
      }
}