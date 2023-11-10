
package Turismo;

public class Aviao extends Aeronave {
    private Passageiro[][] lugares;

    public Aviao(String modelo, int numeroDeFileiras, int assentosPorFileira) {
        super(modelo);
        this.lugares = new Passageiro[numeroDeFileiras][assentosPorFileira];
    }

    public Passageiro getPassageiro(int fileira, int assento) {
        return lugares[fileira][assento];
    }

    public boolean verificaLugarOcupado(int fileira, int assento) {
        return lugares[fileira][assento] != null;
    }

    public void setPassageiro(int fileira, int assento, Passageiro passageiro) {
        lugares[fileira][assento] = passageiro;
    }

    public Passageiro[][] getLugares() {
        return lugares;
    }

    public int getTotalFileiras() {
        return lugares.length;
    }

    public int getTotalAssentos() {
        return lugares[0].length;
    }

    public int getAssentosPorFileira() {
        if (lugares.length > 0) {
            return lugares[0].length;
        }
        return 0;
    }
    public int getAssentosOcupados() {
        int ocupados = 0;
        for (int i = 0; i < lugares.length; i++) {
            for (int j = 0; j < lugares[i].length; j++) {
                if (lugares[i][j] != null) {
                    ocupados++;
                }
            }
        }
        return ocupados;
    }

}