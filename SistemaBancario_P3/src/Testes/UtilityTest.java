package Testes;

import DAO.ContaDao;
import Model.Conta;
import Util.Utility;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilityTest {

    @Test
    void regexTest() {
        assertEquals(true, Utility.Regex("0000124515"));

        //com letra
        assertEquals(false, Utility.Regex("000012451a"));
        //acima da quantidade
        assertEquals(false, Utility.Regex("00001245111"));
    }

}