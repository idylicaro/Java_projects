package Interfaces;

import Model.Conta;

public interface BoxOperations {
    //Implementar ela nos controles onde há  operações de caixa tanto no sistema bancario como no sistema do caixa eletronico
        public void deposito(Conta conta, double valor);
        public boolean saque(Conta conta, double valor);
        public boolean transferencia(Conta remetente, Conta destinatario , double valor);
}
