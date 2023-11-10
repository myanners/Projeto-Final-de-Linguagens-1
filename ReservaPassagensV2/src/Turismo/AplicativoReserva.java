package Turismo;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class AplicativoReserva {
    private static Voo[] voos = new Voo[10];
    private static List<Aviao> listaAeronaves = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            opcao = mostrarMenuPrincipal();
            switch (opcao) {
                case 1:
                    parametrosDoSistema();
                    break;
                case 2:
                    reservaDePassagens();
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != 3);
    }

    private static int mostrarMenuPrincipal() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog("Menu Principal:\n"
                        + "1 - Parâmetros do Sistema\n"
                        + "2 - Reserva de Passagens\n"
                        + "3 - Sair");
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
            }
        }
    }

    private static void parametrosDoSistema() {
        int opcao;
        do {
            opcao = mostrarSubMenuParametros();
            switch (opcao) {
                case 1:
                    cadastrarAeronave();
                    break;
                case 2:
                    cadastrarVoo();
                    break;
                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != 3);
    }

    private static int mostrarSubMenuParametros() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog("Parâmetros do Sistema:\n"
                        + "1 - Cadastrar Aeronave\n"
                        + "2 - Cadastrar Voo\n"
                        + "3 - Voltar");
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
            }
        }
    }
    private static void reservaDePassagens() {
        int opcao;
        do {
            opcao = mostrarMenuReservas();
            switch (opcao) {
                case 1:
                    fazerReserva();
                    break;
                case 2:
                    consultarLugaresVazios();
                    break;
                case 3:
                    consultarReservasRealizadas();
                    break;
                case 4:
                    mostrarDetalhesVooEspecifico();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (opcao != 5);
    }


    private static int mostrarMenuReservas() {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog("Reserva de Passagens:\n"
                        + "1 - Fazer reserva\n"
                        + "2 - Consultar lugares vazios\n"
                        + "3 - Consultar reservas realizadas\n"
                        + "4 - Detalhes de um Voo Específico\n"
                        + "5 - Voltar");
                int opcao = Integer.parseInt(input);
                if (opcao >= 1 && opcao <= 5) {
                    return opcao;
                } else {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um número entre 1 e 5.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido.");
            }
        }
    }

    private static void cadastrarAeronave() {
        String modelo = JOptionPane.showInputDialog("Informe o modelo da aeronave:");

        if (modelo != null && !modelo.trim().isEmpty()) {
            Aviao aviao = new Aviao(modelo, solicitarFileiras(), solicitarAssentosPorFileira());
            listaAeronaves.add(aviao);
            JOptionPane.showMessageDialog(null, "Aeronave cadastrada com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro cancelado ou modelo inválido.");
        }
    }

    private static int solicitarFileiras() {
        int fileiras = 0;
        do {
            try {
                fileiras = Integer.parseInt(JOptionPane.showInputDialog("Informe o número total de fileiras da aeronave:"));
                if (fileiras <= 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um número válido de fileiras.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido de fileiras.");
            }
        } while (fileiras <= 0);
        return fileiras;
    }

    private static int solicitarAssentosPorFileira() {
        int assentos = 0;
        do {
            try {
                assentos = Integer.parseInt(JOptionPane.showInputDialog("Informe o número total de assentos por fileira:"));
                if (assentos <= 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um número válido de assentos por fileira.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido de assentos por fileira.");
            }
        } while (assentos <= 0);
        return assentos;
    }

    private static void cadastrarVoo() {
        if (listaAeronaves.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhuma aeronave cadastrada! Cadastre uma aeronave primeiro.");
            return;
        }

        Aviao aviaoEscolhido = escolherAeronave();

        if (aviaoEscolhido != null) {
            int nroVoo = solicitarNumeroVoo();
            String data = solicitarDataVoo();
            String hora = solicitarHoraVoo();

            Voo voo = new Voo(aviaoEscolhido, nroVoo, data, hora);
            for (int i = 0; i < voos.length; i++) {
                if (voos[i] == null) {
                    voos[i] = voo;
                    JOptionPane.showMessageDialog(null, "Voo cadastrado com sucesso!");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Limite de voos alcançado!");
        } else {
            JOptionPane.showMessageDialog(null, "Cadastro de voo cancelado.");
        }
    }

    private static Aviao escolherAeronave() {
        String[] modelos = new String[listaAeronaves.size()];
        for (int i = 0; i < listaAeronaves.size(); i++) {
            modelos[i] = listaAeronaves.get(i).getModelo();
        }

        String escolha = (String) JOptionPane.showInputDialog(
                null,
                "Escolha uma aeronave:",
                "Cadastro de Voo",
                JOptionPane.QUESTION_MESSAGE,
                null,
                modelos,
                modelos[0]
        );

        if (escolha != null) {
            for (Aviao aviao : listaAeronaves) {
                if (aviao.getModelo().equals(escolha)) {
                    return aviao;
                }
            }
        }
        return null;
    }

    private static int solicitarNumeroVoo() {
        int nro = 0;
        do {
            try {
                nro = Integer.parseInt(JOptionPane.showInputDialog("Informe o número do voo:"));
                if (nro <= 0) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o voo.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um número válido para o voo.");
            }
        } while (nro <= 0);
        return nro;
    }

    private static String solicitarDataVoo() {
        String data = JOptionPane.showInputDialog("Informe a data do voo (DD/MM/YYYY):");
        return data != null ? data : "";
    }

    private static String solicitarHoraVoo() {
        String hora = JOptionPane.showInputDialog("Informe a hora do voo (HH:MM):");
        return hora != null ? hora : "";
    }

    private static void fazerReserva() {
        Voo vooEscolhido = escolherVoo();
        if (vooEscolhido == null) {
            JOptionPane.showMessageDialog(null, "Operação cancelada ou nenhum voo disponível.");
            return;
        }

        Aviao aviao = vooEscolhido.getAeronave();

        boolean valido = false;
        int fileira = -1;
        do {
            try {
                fileira = Integer.parseInt(JOptionPane.showInputDialog("Escolha a fileira (1 a " + aviao.getLugares().length + "):")) - 1;
                if (fileira < 0 || fileira >= aviao.getLugares().length) {
                    JOptionPane.showMessageDialog(null, "Fileira inválida!");
                    continue;
                }
                valido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira uma fileira válida.");
            }
        } while (!valido);

        valido = false;
        int assento = -1;
        do {
            try {
                assento = Integer.parseInt(JOptionPane.showInputDialog("Escolha o assento (1 a " + aviao.getLugares()[fileira].length + "):")) - 1;
                if (assento < 0 || assento >= aviao.getLugares()[fileira].length) {
                    JOptionPane.showMessageDialog(null, "Assento inválido!");
                    continue;
                }
                if (aviao.verificaLugarOcupado(fileira, assento)) {
                    JOptionPane.showMessageDialog(null, "Este assento já está ocupado!");
                    continue;
                }
                valido = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor, insira um assento válido.");
            }
        } while (!valido);

        String nome = "";
        while (nome.trim().isEmpty()) {
            nome = JOptionPane.showInputDialog("Digite o nome do passageiro:");
            if (nome.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome não pode ser em branco!");
            }
        }

        String cpf = "";
        while (cpf.trim().isEmpty()) {
            cpf = JOptionPane.showInputDialog("Digite o CPF do passageiro:");
            if (cpf.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "CPF não pode ser em branco!");
            }
        }

        Passageiro novoPassageiro = new Passageiro(nome, cpf);
        aviao.setPassageiro(fileira, assento, novoPassageiro);
        JOptionPane.showMessageDialog(null, "Reserva feita com sucesso!");
    }

    private static Voo escolherVoo() {
        List<Voo> voosDisponiveis = new ArrayList<>();
        for (Voo v : voos) {
            if (v != null) {
                voosDisponiveis.add(v);
            }
        }

        if (voosDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nenhum voo disponível.");
            return null;
        }

        String[] opcoes = new String[voosDisponiveis.size()];
        for (int i = 0; i < voosDisponiveis.size(); i++) {
            opcoes[i] = "Voo Nº " + voosDisponiveis.get(i).getNro() +
                    " - Data: " + voosDisponiveis.get(i).getData() +
                    " - Hora: " + voosDisponiveis.get(i).getHora();
        }

        String escolha = (String) JOptionPane.showInputDialog(
                null,
                "Escolha um voo:",
                "Reserva de Passagem",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        for (Voo v : voosDisponiveis) {
            if (("Voo Nº " + v.getNro() + " - Data: " + v.getData() + " - Hora: " + v.getHora()).equals(escolha)) {
                return v;
            }
        }

        return null;
    }

    private static Passageiro criarPassageiro() {
        String nome = JOptionPane.showInputDialog("Informe o nome do passageiro:");
        String cpf = JOptionPane.showInputDialog("Informe o CPF do passageiro:");

        if (nome == null || nome.trim().isEmpty() || cpf == null || cpf.trim().isEmpty()) {
            return null;
        }

        return new Passageiro(nome, cpf);
    }

    private static int[] escolherAssento(Voo voo) {
        Aviao aviao = voo.getAeronave();
        int fileiras = aviao.getLugares().length;
        int assentos = aviao.getLugares()[0].length;

        int fileira = 0;
        int assento = 0;
        boolean assentoEscolhido = false;

        while (!assentoEscolhido) {
            try {
                fileira = Integer.parseInt(JOptionPane.showInputDialog("Escolha a fileira (1 a " + fileiras + "):")) - 1;
                assento = Integer.parseInt(JOptionPane.showInputDialog("Escolha o assento (1 a " + assentos + "):")) - 1;

                if (fileira >= 0 && fileira < fileiras && assento >= 0 && assento < assentos) {
                    if (aviao.verificaLugarOcupado(fileira, assento)) {
                        JOptionPane.showMessageDialog(null, "Assento já está ocupado. Escolha outro.");
                    } else {
                        assentoEscolhido = true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Assento inválido. Escolha novamente.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Tente novamente.");
            }
        }

        return new int[]{fileira, assento};
    }

    private static void consultarLugaresVazios() {
        Voo vooEscolhido = escolherVoo();
        if (vooEscolhido == null) {
            return;
        }

        Aviao aviao = vooEscolhido.getAeronave();
        int totalLugaresVazios = 0;

        for (int i = 0; i < aviao.getLugares().length; i++) {
            for (int j = 0; j < aviao.getLugares()[i].length; j++) {
                if (!aviao.verificaLugarOcupado(i, j)) {
                    totalLugaresVazios++;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Total de lugares vazios no voo " + vooEscolhido.getNro() + ": " + totalLugaresVazios);
    }

    private static void consultarReservasRealizadas() {
        Voo vooEscolhido = escolherVoo();
        if (vooEscolhido == null) {
            return;
        }

        Aviao aviao = vooEscolhido.getAeronave();
        StringBuilder sb = new StringBuilder();

        sb.append("<html><h2>Assentos Reservados</h2><table border='1'>");

        // Cabeçalho para os números dos assentos
        sb.append("<tr><td></td>");  // Espaço vazio no canto superior esquerdo
        for (int j = 0; j < aviao.getLugares()[0].length; j++) {
            sb.append("<td>").append(j + 1).append("</td>");  // Adiciona numeração dos assentos
        }
        sb.append("</tr>");

        for (int i = 0; i < aviao.getLugares().length; i++) {
            sb.append("<tr>");

            // Número da fileira no começo de cada linha
            sb.append("<td>").append(i + 1).append("</td>");

            for (int j = 0; j < aviao.getLugares()[i].length; j++) {
                sb.append("<td>");
                if (aviao.verificaLugarOcupado(i, j)) {
                    sb.append(aviao.getPassageiro(i, j).getNome());
                } else {
                    sb.append("-");
                }
                sb.append("</td>");
            }
            sb.append("</tr>");
        }
        sb.append("</table></html>");

        JOptionPane.showMessageDialog(null, sb.toString(), "Reservas no Voo " + vooEscolhido.getNro(), JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarDetalhesVooEspecifico() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Voo v : voos) {
            if (v != null) {
                listModel.addElement("Número do Voo: " + v.getNro());
            }
        }

        JList<String> listaVoos = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(listaVoos);
        JOptionPane.showMessageDialog(null, scrollPane, "Selecione um voo", JOptionPane.PLAIN_MESSAGE);

        int numeroVoo;
        try {
            numeroVoo = Integer.parseInt(listaVoos.getSelectedValue().split(":")[1].trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Seleção inválida.");
            return;
        }

        for (Voo v : voos) {
            if (v != null && v.getNro() == numeroVoo) {
                int totalAssentos = v.getAeronave().getTotalFileiras() * v.getAeronave().getAssentosPorFileira();
                int assentosOcupados = v.getAeronave().getAssentosOcupados();
                int assentosLivres = totalAssentos - assentosOcupados;

                String detalhes = "Número do Voo: " + v.getNro() + "\n"
                        + "Modelo da Aeronave: " + v.getAeronave().getModelo() + "\n"
                        + "Data: " + v.getData() + "\n"
                        + "Hora: " + v.getHora() + "\n"
                        + "Quantidade total de assentos: " + totalAssentos + "\n"
                        + "Quantidade de assentos ocupados: " + assentosOcupados + "\n"
                        + "Quantidade de assentos livres: " + assentosLivres;
                JOptionPane.showMessageDialog(null, detalhes);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Voo não encontrado.");
    }


}
