package extintoresfire1.Impresion;

import extintoresfire1.ControladorBD.ControladorContado;
import extintoresfire1.ControladorBD.ControladorCreditos;
import extintoresfire1.modelos.Contado;
import extintoresfire1.modelos.Creditos;
import java.awt.*;
import java.awt.print.*;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class ImpresionContadoCredito extends javax.swing.JFrame {

    String ruta;
    ControladorContado ctrlContado;
    ControladorCreditos ctrlCredito;

    public ImpresionContadoCredito() {
        initComponents();
        ctrlContado = new ControladorContado();
        ctrlCredito = new ControladorCreditos();
        JOptionPane.setRootFrame(this);

    }

    public void TituloFactura() {
        jTAsalida.append("\n");
        jTAsalida.append("\n");
        jTAsalida.append("\n");
        jTAsalida.append("\n");
        jTAsalida.append("          Extintores Fire");
        jTAsalida.append("\n");

        jTAsalida.append("      Kendall Umaña Alvarado");
        jTAsalida.append("\n");
        jTAsalida.append("         Céd.N° 2-736-498");
        jTAsalida.append("\n");
        jTAsalida.append("     www.extintoresfire.com");
        jTAsalida.append("\n");
        jTAsalida.append("    Oficina central:2474-3967");
        jTAsalida.append("\n");
        jTAsalida.append("        Celular:8418-0003");
        jTAsalida.append("\n");
        jTAsalida.append("\n");
    }

    public void DatosCliente(String fecha, String cliente, String empresa, String direccion, String telefono, String cedula) {

        jTAsalida.append("Fecha: " + fecha);
        jTAsalida.append("\n");
        jTAsalida.append("Cliente:" + cliente);
        jTAsalida.append("\n");
        jTAsalida.append("Cédula:" + cedula);
        jTAsalida.append("\n");
        jTAsalida.append("Empresa: " + empresa);
        jTAsalida.append("\n");
        jTAsalida.append("Dirección:" + direccion);
        jTAsalida.append("\n");
        jTAsalida.append("Teléfono:" + telefono);
        jTAsalida.append("\n");
        jTAsalida.append("--------------------------------");
        jTAsalida.append("                     E*      G**");
    }

    public void ProductosFactura(String[][] productos) {

        int mide = 0;
        for (int i = 0; i < 20; i++) {
            jTAsalida.append("\n");
            for (int j = 0; j < 4; j++) {
                if (j == 0 && productos[i][j] == null) {
                    return;
                }
                if (productos[i][j]==null) {

                } else {
                    if (j == 2) {
                        if (productos[i][1].length() <= 5) {

                            jTAsalida.append("         ");
                        }
                        if (productos[i][1].length() == 6) {
                            jTAsalida.append("        ");
                        }
                        if (productos[i][1].length() >= 7 && productos[i][1].length() < 8) {
                            jTAsalida.append("       ");
                        }
                        if (productos[i][1].length() == 8) {
                            jTAsalida.append("      ");
                        }

                        if (productos[i][1].length() == 9) {
                            jTAsalida.append("     ");

                        }
                        if (productos[i][1].length() == 10) {
                            jTAsalida.append("    ");
                        }
                        if (productos[i][1].length() == 11) {
                            jTAsalida.append("   ");
                        }
                        if (productos[i][1].length() == 12) {
                            jTAsalida.append("   ");
                        }
                        if (productos[i][1].length() == 13) {
                            jTAsalida.append("  ");
                        }

                        mide = productos[i][j].length();
                        if (productos[i][0].length() == 2) {
                            if (mide <= 4) {
                                jTAsalida.append("  " + productos[i][j]);
                            }
                            if (mide == 5 || mide == 6) {
                                jTAsalida.append(" " + productos[i][j]);
                            }

                            if (mide >= 7 && mide < 9) {
                                jTAsalida.append(productos[i][j]);
                            }

                            if (mide > 8) {
                                JOptionPane.showMessageDialog(null, "La cantidad es muy grande, porfavor ajusta el programa");
                                return;
                            }

                        } else {

                            if (mide <= 4) {
                                jTAsalida.append("   " + productos[i][j]);
                            }
                            if (mide == 5 || mide == 6) {
                                jTAsalida.append("  " + productos[i][j]);
                            }

                            if (mide >= 7 && mide < 9) {
                                jTAsalida.append(" " + productos[i][j]);
                            }

                            if (mide > 8) {
                                JOptionPane.showMessageDialog(null, "La cantidad es muy grande, porfavor ajusta el programa");
                                return;
                            }
                        }

                    }
                    if (j == 3) {
                        if (productos[i][1].length() <= 5) {
                            jTAsalida.append("                ");
                        }
                        if (productos[i][1].length() == 6) {
                            jTAsalida.append("               ");
                        }
                        if (productos[i][1].length() >= 7 && productos[i][1].length() < 8) {
                            jTAsalida.append("              ");
                        }
                        if (productos[i][1].length() == 8) {
                            jTAsalida.append("             ");
                        }

                        if (productos[i][1].length() == 9) {
                            jTAsalida.append("            ");

                        }
                        if (productos[i][1].length() == 10) {
                            jTAsalida.append("           ");
                        }
                        if (productos[i][1].length() == 11) {
                            jTAsalida.append("          ");
                        }
                        if (productos[i][1].length() == 12) {
                            jTAsalida.append("         ");
                        }
                        if (productos[i][1].length() == 13) {
                            jTAsalida.append("        ");
                        }
                        if (productos[i][1].length() == 14) {
                            jTAsalida.append("       ");
                        }
                        if (productos[i][1].length() == 15) {
                            jTAsalida.append("      ");
                        }
                        if (productos[i][1].length() == 16) {
                            jTAsalida.append("     ");
                        }
                        if (productos[i][1].length() == 17) {
                            jTAsalida.append("    ");
                        }
                        // lo que mide lo que cuesta cada producto
                        mide = productos[i][j].length();
                        if (productos[i][0].length() == 2) {
                            if (mide <= 4) {
                                jTAsalida.append("   " + productos[i][j]);
                            }
                            if (mide == 5 || mide == 6) {
                                jTAsalida.append(" " + productos[i][j]);
                            }

                            if (mide == 7 || mide == 8) {
                                jTAsalida.append(productos[i][j]);
                            }

                            if (mide > 8) {
                                JOptionPane.showMessageDialog(null, "La cantidad es muy grande, porfavor ajusta el programa");
                                return;
                            }

                        } else {

                            if (mide <= 4) {
                                jTAsalida.append("    " + productos[i][j]);
                            }
                            if (mide == 5 || mide == 6) {
                                jTAsalida.append("  " + productos[i][j]);
                            }

                            if (mide == 7) {
                                jTAsalida.append(" " + productos[i][j]);
                            }
                            if (mide == 8) {
                                jTAsalida.append(productos[i][j]);
                            }

                            if (mide > 8) {
                                JOptionPane.showMessageDialog(null, "La cantidad es muy grande, porfavor ajusta el programa");
                                return;
                            }
                        }

                    }
                    if (j != 3 && j != 2) {
                        jTAsalida.append(productos[i][j]);
                        jTAsalida.append(" ");
                    }

                }

            }

        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jBImprimir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTAsalida = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Imprimir el contenido de un JText Area");
        setMinimumSize(new java.awt.Dimension(400, 400));

        jPanel2.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        jBImprimir.setMnemonic('I');
        jBImprimir.setText("Imprimir");
        jBImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBImprimirActionPerformed(evt);
            }
        });
        jPanel2.add(jBImprimir);

        jPanel1.add(jPanel2);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        jTAsalida.setColumns(20);
        jTAsalida.setRows(5);
        jScrollPane2.setViewportView(jTAsalida);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBImprimirActionPerformed
        Imprimir();
    }//GEN-LAST:event_jBImprimirActionPerformed

    public void Imprimir() {

        PaginationExample pagination = new PaginationExample();
        pagination.imprimirnomina();
    }

    public void DatosFinalFactura(String totalFactura) {
        jTAsalida.append("--------------------------------");
        jTAsalida.append("\n");
        jTAsalida.append("I.V.I          ");
        jTAsalida.append("Total: ");
        jTAsalida.append(totalFactura);
        jTAsalida.append("\n");
        jTAsalida.append("Exento* Gravado**");
        jTAsalida.append("\n");
        jTAsalida.append("--------------------------------");

        jTAsalida.append("BN 200-01-163-005870-4");
        jTAsalida.append("\n");
        jTAsalida.append("Kendall Umaña Alvarado ");
        jTAsalida.append("\n");

        jTAsalida.append("________________________________");
        jTAsalida.append("\n");
        jTAsalida.append("Autorizado Mediante oficio No 4521000004875 del día 25-07-2010");
        jTAsalida.append("\n");
        jTAsalida.append("de la Dirección General de Tributación Directa");
        jTAsalida.append("\n");
        jTAsalida.append("________________________________");
        jTAsalida.append("\n");

    }

    public void tipoFactura(String tipoFactura) {

        if (tipoFactura.equals("Contado")) {
            java.util.List<Contado> contado = this.ctrlContado.Refrescar();
            int ultimo = contado.size() - 1;
            int ultimaFactura = contado.get(ultimo).getNumerofactura();
            jTAsalida.append("Contado                 " + "N° " + ultimaFactura);
        } else {
            java.util.List<Creditos> creditos = this.ctrlCredito.Refrescar();
            int ultimo = creditos.size() - 1;
            int ultimaFactura = creditos.get(ultimo).getNumerofactura();
            jTAsalida.append("Crédito                 " + "N° " + ultimaFactura);
        }

        jTAsalida.append("\n");
        jTAsalida.append("\n");
        jTAsalida.append("\n");
        jTAsalida.append("\n");

    }

    public class PaginationExample implements Printable {

        //Se obtienen las lineas de texto del JTextArea, la linea de texto finaliza cuando se encuentra el caracter de nueva linea \n
        StringTokenizer lineasdetexto = new StringTokenizer(jTAsalida.getText(), "\n", true);

        //Se obtiene el total de lineas de texto
        int totallineas = lineasdetexto.countTokens();

        int[] paginas;  // Arreglo de número de paginas que se necesitaran para imprimir todo el texto 

        String[] textoLineas; //Lineas de texto que se imprimiran en cada hoja

        //Metodo que se crea por default cuando una clase implementa a Printable
        public int print(Graphics g, PageFormat pf, int pageIndex)
                throws PrinterException {
            //Se establece la fuente, el tipo, el tamaño, la metrica según la fuente asignada, 
            //obtiene la altura de cada linea de texto para que todas queden iguales
            Font font = new Font("Serif", Font.PLAIN, 5);
            FontMetrics metrics = g.getFontMetrics(font);
            int altodelinea = metrics.getHeight();
            //Calcula el número de lineas por pagina y el total de paginas
            if (paginas == null) {
                initTextoLineas();
                //Calcula las lineas que le caben a cada página dividiendo la altura imprimible entre la altura de la linea de texto
                int lineasPorPagina = (int) (pf.getImageableHeight() / altodelinea);
                //Calcula el numero de páginas dividiendo el total de lineas entre el numero de lineas por página
                int numeroPaginas = (textoLineas.length - 1) / lineasPorPagina;
                paginas = new int[numeroPaginas];
                for (int b = 0; b < numeroPaginas; b++) {
                    paginas[b] = (b + 1) * lineasPorPagina;
                }
            }
            //Si se recibe un indice de página mayor que el total de páginas calculadas entonces 
            //retorna NO_SUCH_PAGE para indicar que tal pagina no existe 
            if (pageIndex > paginas.length) {
                return NO_SUCH_PAGE;
            }
            /*Por lo regular cuando dibujamos algun objeto lo coloca en la coordenada (0,0), esta coordenada 
             * se encuentra fuera del área imprimible, por tal motivo se debe trasladar la posicion de las lineas de texto
             * según el área imprimible del eje X y el eje Y 
             */

            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pf.getImageableX(), pf.getImageableY());
            /*Dibujamos cada línea de texto en cada página,
             * se aumenta a la posición 'y' la altura de la línea a cada línea de texto para evitar la saturación de texto 
             */

            int y = 0;
            int start = (pageIndex == 0) ? 0 : paginas[pageIndex - 1];
            int end = (pageIndex == paginas.length) ? textoLineas.length : paginas[pageIndex];
            for (int line = start; line < end; line++) {
                y += altodelinea;
                g.drawString(textoLineas[line], 0, y);
            }
            /* Retorna PAGE_EXISTS para indicar al invocador que esta página es parte del documento impreso
             */
            return PAGE_EXISTS;
        }

        /* Agrega las lineas de texto al arreglo */
        public void initTextoLineas() {
            if (textoLineas == null) {
                int numLineas = totallineas;
                textoLineas = new String[numLineas];
                //Se llena el arreglo que contiene todas las lineas de texto
                while (lineasdetexto.hasMoreTokens()) {
                    for (int i = 0; i < numLineas; i++) {
                        textoLineas[i] = lineasdetexto.nextToken();
                    }
                }
            }
        }

        //Este metodo crea un objeto Printerjob el cual es inicializado y asociado con la impresora por default
        public void imprimirnomina() {
            PrinterJob job = PrinterJob.getPrinterJob();

            job.setPrintable(this);
            //Si el usuario presiona imprimir en el dialogo de impresión, 
            //entonces intenta imprimir todas las lineas de texto

            job.setCopies(1);
            try {
                job.print();
//            boolean ok = job.printDialog();
//            if (ok) {
//                try {
//                    job.print();
//                } catch (PrinterException ex) {
//                    /* The job did not successfully complete */
//                }
//            }
            } catch (PrinterException ex) {
                JOptionPane.showMessageDialog(null, "No se ha podido imprimir, apaga y vuelve a prender tu impresora");
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBImprimir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTAsalida;
    // End of variables declaration//GEN-END:variables
}
