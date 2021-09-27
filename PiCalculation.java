/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package picalculation;

/**
 *
 * @author torke
 */
public class PiCalculation {

    
    private static int calcColision( float time, float x1, float x2, float m1, float m2, float v1, float v2 ) {
    
        float t;
        
        //System.out.println( "Time: " + time + " - x1: " + x1 + ", x2: " + x2 + " v1: " + v1 + " v2: " + v2 );
        
        if ( v2 > 0 && v2 >= Math.abs(v1) ) return 0;
        
        //Sjekk hva som skjer først
        if ( ( v1 != 0 && x1 != 0 && -x1 / v1 < ( x2 - x1 ) / ( v1 - v2 ) ) || x1 == x2) {
        
            //Første krasj i veggen
            t = -x1 / v1;
            return calcColision( time + t, 0, x2 + t*v2, m1, m2, v1 * -1, v2 ) + 1;
        } else {
        
            t = ( x2 - x1 ) / ( v1 - v2 );
            float pos = x1 + v1 * t;
            return calcColision( time + t, pos, pos, m1, m2, 
                                (m1-m2)/(m1+m2)*v1+2*m2/(m1+m2)*v2, 
                                2*m1/(m1+m2)*v1 + (m2-m1)/(m1+m2)*v2) + 1;
        }
    }
    
    private static int calcPiWhile( float time, float x1, float x2, float m1, float m2, float v1, float v2 ) {
    
        float t;
        int count = 0;
        while ( !(v2 > 0 && v2 >= Math.abs(v1)) ) {
        
            //System.out.println( "Time: " + time + " - x1: " + x1 + ", x2: " + x2 + " v1: " + v1 + " v2: " + v2 );
            
            if ( ( v1 != 0 && x1 != 0 && -x1 / v1 < ( x2 - x1 ) / ( v1 - v2 ) ) || x1 == x2) {

                //Første krasj i veggen
                t = -x1 / v1;
                time += t;
                x1 = 0;
                x2 += t*v2;
                v1 *= -1;
            } else {

                t = ( x2 - x1 ) / ( v1 - v2 );
                float pos = x1 + v1 * t;
                time += t;
                x1 = pos;
                x2 = pos; 
                float v1T = (m1-m2)/(m1+m2)*v1+2*m2/(m1+m2)*v2;
                v2 = 2*m1/(m1+m2)*v1 + (m2-m1)/(m1+m2)*v2;
                v1 = v1T;
            }
            
            count++;
            if ( count % 1000 == 0 ) System.out.println( count );
        }
     
        return count;
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        float m1 = 1;
        float m2 = 100000000;
        float v1_0 = 0;
        float v2_0 = -1f;
        float x1 = 10;
        float x2 = 20;
        
        // Recursive
        //System.out.println( "Result: " + calcColision( 0, x1, x2, m1, m2, v1_0, v2_0 ) );
        
        // While
        System.out.println( "Result: " + calcPiWhile( 0, x1, x2, m1, m2, v1_0, v2_0 ) );       
        
    }
    
}
