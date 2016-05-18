import java.util.Scanner;
import java.io.*;

class GeomСalculations {
    private double coordinateX = 0;
    private double coordinateY = 0;
    private double rotationAngleInDegrees = 0;//угол поворота
    private double tiltAngleInDegrees = 0;//угол наклона - падения
    private double cornerSize = 0;//the size of the verge
    private double[] afterTheCount1 = {0, 0, 0, 0, 0, 0, 0}; //x,y, угол поворота, угол наклона,maxh,fr,RS
    private double[] afterTheCount2 = {0, 0, 0, 0, 0, 0, 0}; //x,y, угол поворота, угол наклона,maxh,fr,RS
    private double way = 0;
    private boolean readiness = true;
    private static final String TEXT_ENTER_X = "Enter coordinat X:";
    private static final String TEXT_ENTER_Y = "Enter coordinat Y:";
    private static final String TEXT_ENTER_TILT = "Enter the tilt angle:";
    private static final String TEXT_ENTER_ROTATION = "Enter the rotation angle:";
    private static final String TEXT_ENTER_SIZE = "Enter the sithe of the verge:";

    void start() {
        try (FileWriter writer = new FileWriter("D:\\dev\\Gambelia\\reflection.txt", true)) {
            reading();
            way = 0;
            double[] dr = {0, 0};
            readiness = true;
            String ways = "";
            ways = Double.toString(coordinateX);
            writer.write("\r\nX=" + ways + "; ");
            ways = Double.toString(coordinateY);
            writer.write("Y="+ ways + "; ");
            ways = Double.toString(rotationAngleInDegrees);
            writer.write("RotatA=" + ways + "; ");
            ways = Double.toString(tiltAngleInDegrees);
            writer.write("TiltA=" + ways + "; ");
            ways = Double.toString(cornerSize);
            writer.write("CornSize=" + ways + "; \r\n");


            if (readiness == true) {

                afterTheCount1 = choiceoOfMethod(coordinateX, coordinateY, rotationAngleInDegrees, tiltAngleInDegrees);
                if (readiness == false) {
                    way = 0;
                    System.out.println(way);
                    ways = Double.toString(way);
                    writer.write(ways + "\r\n");
                }
                if (readiness == true) {
                    dr = findingDR(coordinateX, coordinateY, rotationAngleInDegrees, tiltAngleInDegrees, cornerSize); //dr,df
                    double re = findingRE(afterTheCount1[4], dr[0], afterTheCount1[5], tiltAngleInDegrees);
                    way = findingWay(re, afterTheCount1[6], afterTheCount1[0]);
                    System.out.println(way);
                    ways = Double.toString(way);
                    writer.write("#1 " + ways);
                }
                if (readiness == true) {
                    double[] ou_s = {0, 0};
                    double[] neweCord = {0, 0};
                    ou_s = findingRef(0.1, 1.3, afterTheCount1[3]);
                    way = way + ou_s[1];
                    neweCord = modulationPoint(afterTheCount1[0], afterTheCount1[1], afterTheCount1[2], ou_s[0]);
                    afterTheCount1[0] = neweCord[0];
                    afterTheCount1[1] = neweCord[1];

                    afterTheCount2 = choiceoOfMethod(afterTheCount1[0], afterTheCount1[1], afterTheCount1[2], afterTheCount1[3]);
                    way = findingWay(way, afterTheCount2[6], afterTheCount2[0]);
                    System.out.println(way);
                    ways = Double.toString(way);
                    writer.write("\r\n" + "#2 " +ways + "\n");
                }
                if (readiness == true) {
                    dr = findingDR(afterTheCount2[0], afterTheCount2[1], afterTheCount2[2], afterTheCount2[3], cornerSize);//dr,df
                    double oq = findingMaxH(cornerSize, afterTheCount1[0]);
                    double RE = findingRE(oq, dr[0], (dr[1] - dr[0]), afterTheCount2[3]);
                    way = way + RE;
                    System.out.println(way);
                    ways = Double.toString(way);
                    writer.write("\r\n" + "#3 " +ways + "\n");
                }
                System.out.println("\n");
                writer.write("\r\n");
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }

    private void checkingCenter(double x, double y, double rot) {
        double tanJRO = y / x;
        double jro = Math.toDegrees(Math.atan(tanJRO));
        double orj = 360 - jro;
        if (orj == rot) {
            readiness = false;
        }
    }

//    void test() {
//        for (int xi = 1; xi < 50; xi++) {
//            for (int yi = 51 - xi; yi > 0; yi--) {
//                coordinateX = xi;
//                coordinateY = yi;
//                rotationAngleInDegrees = 120;
//                tiltAngleInDegrees = 60;
//                cornerSize = 50;
//                System.out.println(xi + " _" + yi + " _" + rotationAngleInDegrees + " _" + tiltAngleInDegrees);
//                try (FileWriter writer = new FileWriter("D:\\dev\\Gambelia\\reflection.txt", true)) {
//                    way = 0;
//                    double[] dr = {0, 0};
//                    readiness = true;
//                    String ways = "";
//                    ways = Double.toString(coordinateX);
//                    writer.write("\r\nX=" + ways + "; ");
//                    ways = Double.toString(coordinateY);
//                    writer.write("Y="+ ways + "; ");
//                    ways = Double.toString(rotationAngleInDegrees);
//                    writer.write("RotatA=" + ways + "; ");
//                    ways = Double.toString(tiltAngleInDegrees);
//                    writer.write("TiltA=" + ways + "; ");
//                    ways = Double.toString(cornerSize);
//                    writer.write("CornSize=" + ways + "; \r\n");
//
//
//                    if (readiness == true) {
//                        afterTheCount1 = choiceoOfMethod(coordinateX, coordinateY, rotationAngleInDegrees, tiltAngleInDegrees);
//                        if (readiness == false) {
//                            way = 0;
//                            System.out.println(way);
//                            ways = Double.toString(way);
//                            writer.write(ways + "\r\n");
//                        }
//                        if (readiness == true) {
//                            dr = findingDR(coordinateX, coordinateY, rotationAngleInDegrees, tiltAngleInDegrees, cornerSize); //dr,df
//                            double re = findingRE(afterTheCount1[4], dr[0], afterTheCount1[5], tiltAngleInDegrees);
//                            way = findingWay(re, afterTheCount1[6], afterTheCount1[0]);
//                            System.out.println(way);
//                            ways = Double.toString(way);
//                            writer.write("#1 " + ways);
//                        }
//                        if (readiness == true) {
//                            double[] ou_s = {0, 0};
//                            double[] neweCord = {0, 0};
//                            ou_s = findingRef(0.1, 1.3, afterTheCount1[3]);
//                            way = way + ou_s[1];
//                            neweCord = modulationPoint(afterTheCount1[0], afterTheCount1[1], afterTheCount1[2], ou_s[0]);
//                            afterTheCount1[0] = neweCord[0];
//                            afterTheCount1[1] = neweCord[1];
//                            afterTheCount2 = choiceoOfMethod(afterTheCount1[0], afterTheCount1[1], afterTheCount1[2], afterTheCount1[3]);
//                            way = findingWay(way, afterTheCount2[6], afterTheCount2[0]);
//                            System.out.println(way);
//                            ways = Double.toString(way);
//                            writer.write("\r\n" + "#2 " +ways + "\n");
//                        }
//                        if (readiness == true) {
//                            dr = findingDR(afterTheCount2[0], afterTheCount2[1], afterTheCount2[2], afterTheCount2[3], cornerSize);//dr,df
//                            double oq = findingMaxH(cornerSize, afterTheCount1[0]);
//                            double RE = findingRE(oq, dr[0], (dr[1] - dr[0]), afterTheCount2[3]);
//                            way = way + RE;
//                            System.out.println(way);
//                            ways = Double.toString(way);
//                            writer.write("\r\n" + "#3 " +ways + "\n");
//                        }
//                        System.out.println("\n");
//                        writer.write("\r\n");
//                    }
//                } catch (IOException ex) {
//
//                    System.out.println(ex.getMessage());
//                }
//            }
//        }
//    }

    private double[] choiceoOfMethod(double x, double y, double rotationAngle, double tiltAngle) {
        double[] resultChoice = {0, 0, 0, 0, 0, 0, 0};
        if (0 == rotationAngle || 360 == rotationAngle || 270 == rotationAngle) {
            way = 0;
            readiness = false;
        }
        if (90 == rotationAngle) {
            resultChoice[6] = _90180(cornerSize, y, tiltAngle, x, rotationAngle);
            readiness = false;
        }
        if (180 == rotationAngle) {
            resultChoice[6] = _90180(cornerSize, y, tiltAngle, x, rotationAngle);
            readiness = false;
        }
        if (0 < rotationAngle && 90 > rotationAngle) {
            resultChoice = afgo(x, y, rotationAngle, tiltAngle); //{FO,FS,fsg,angl,maxh,fr,RS}
        }
        if (90 < rotationAngle && 180 > rotationAngle) {
            double tanORJ = 0;
            double jro = 0;
            tanORJ = y / x;
            jro = 360 - Math.toDegrees(Math.atan(tanORJ));
            if (jro > (180 + rotationAngle)) {
                resultChoice = agfo(x, y, rotationAngle, tiltAngle); //{FO,FS,fsg,angl,maxh,fr,RS}
            }
            if (jro < (180 + rotationAngle)) {
                resultChoice = ofjb(x, y, rotationAngle, tiltAngle); //{FO,FS,fsg,angl,maxh,fr,RS}
            }
        }
        if (180 < rotationAngle && 270 > rotationAngle) {
            resultChoice = ojfb(x, y, rotationAngle, tiltAngle); //{FO,FS,fsg,angl,maxh,fr,RS
        }
        if(270 >)
        return resultChoice;
    }

    private void reading() {
        Scanner expressionElement = new Scanner(System.in);
        System.out.println(TEXT_ENTER_X);
        coordinateX = expressionElement.nextDouble();
        System.out.println(TEXT_ENTER_Y);
        coordinateY = expressionElement.nextDouble();
        System.out.println(TEXT_ENTER_TILT);
        tiltAngleInDegrees = expressionElement.nextInt();
        System.out.println(TEXT_ENTER_ROTATION);
        rotationAngleInDegrees = expressionElement.nextInt();
        System.out.println(TEXT_ENTER_SIZE);
        cornerSize = expressionElement.nextInt();
    }

    private double[] srfUniversal(double fr, double srf) {
        double[] resaultU = {0, 0, 0, 0, 0, 0, 0};
        resaultU[1] = fr * Math.tan(Math.toRadians(srf)); // return one of the coordinats fs
        resaultU[6] = fr / Math.cos(Math.toRadians(srf));//rs
        return resaultU; //resault {FS,0,0,0,0,0,RS}
    }

    private double[] afgo(double go, double gr, double jrd, double tiltAngInDegrees) {
        double frg = 0;
        double fsg = 0;
        double[] resaultAfgo = {0, 0, 0, 0, 0, 0, 0};
        double fg = 0;
        double fo = 0;
        double fr = 0;
        double tanGSF = 0;
        double angl = 0;
        double maxh = 0;
        double gsf = 0;
        frg = 90 - jrd;
        fg = gr * Math.tan(Math.toRadians(frg));
        fo = fg + go; //return this is x, one of coordinats
        fr = gr / Math.cos(Math.toRadians(frg));
        resaultAfgo = srfUniversal(fr, tiltAngInDegrees); //resault {0,FS,0,0,0,0,RS}
        if (cornerSize > fo) {
            maxh = findingMaxH(cornerSize, fo);
            if (maxh > resaultAfgo[1]) {
                tanGSF = fg / resaultAfgo[1];
                gsf = Math.toDegrees(Math.atan(tanGSF));
                fsg = 360 - gsf; // искомый угол поворота
                angl = tiltAngle(gsf, resaultAfgo[1], go);
                resaultAfgo[0] = fo;
                resaultAfgo[2] = fsg;
                resaultAfgo[3] = angl;
                resaultAfgo[4] = maxh;
                resaultAfgo[5] = fr;
                return resaultAfgo;
            } else {
                resaultAfgo[6] = elseeMaxhMoreRes(go, gr, jrd, tiltAngInDegrees, fr, maxh);
            }
        } else {
            double[] dr_df = {0, 0};
            dr_df = findingDR(go, gr, jrd, tiltAngInDegrees, cornerSize);
            double dg = gr * Math.tan(Math.toRadians(jrd));
            maxh = findingMaxH(cornerSize, (dg + gr));
            double rf = dr_df[1] - dr_df[0];
            resaultAfgo[6] = findingRE(maxh, rf, dr_df[0], tiltAngInDegrees);
            readiness = false;
        }
        return resaultAfgo;
    }

    private double[] agfo(double go, double gr, double jrd, double tiltAngleInDegrees) {
        double grf = 0;
        double fsg = 0;
        double[] resaultAgfo = {0, 0, 0, 0, 0, 0, 0};
        double fg = 0;
        double fo = 0;
        double fr = 0;
        double tanFSG = 0;
        double angl = 0;
        grf = jrd - 90;
        fg = gr * Math.tan(Math.toRadians(grf));
        fo = go - fg;//return this is x, one of coordinats
        if (cornerSize > fo) {
            double maxh = findingMaxH(cornerSize, fo);
            fr = gr / Math.cos(Math.toRadians(grf));
            resaultAgfo = srfUniversal(fr, tiltAngleInDegrees);//resault {0,FS,0,0,0,0,RS}
            if (maxh > resaultAgfo[1]) {
                tanFSG = fg / resaultAgfo[1];
                fsg = Math.toDegrees(Math.atan(tanFSG));// искомый угол поворота
                angl = tiltAngle(fsg, resaultAgfo[1], go);
                resaultAgfo[0] = fo;
                resaultAgfo[2] = fsg;
                resaultAgfo[3] = angl;
                resaultAgfo[4] = maxh;
                resaultAgfo[5] = fr;
                return resaultAgfo;
            } else {

                resaultAgfo[6] = elseeMaxhMoreRes(go, gr, jrd, tiltAngleInDegrees, fr, maxh);

            }
        } else {
            resaultAgfo[6] = -10000.0;
        }
        return resaultAgfo;// не обработано при 2х
    }

    private double[] ofjb(double rj, double oj, double jrd, double tiltAngleInDegrees) {
        double frj = 0;
        double ksj = 0;
        double[] resaultOfjb = {0, 0, 0, 0, 0, 0, 0};
        double fj = 0;
        double fo = 0;
        double fr = 0;
        double tanJSF = 0;
        double jsf = 0;
        double angl = 0;
        frj = 180 - jrd;
        fj = rj * Math.tan(Math.toRadians(frj));
        fo = oj - fj;//return this is x, one of coordinats
        if (cornerSize > fo) {
            double maxh = findingMaxH(cornerSize, fo);
            fr = rj / Math.cos(Math.toRadians(frj));
            resaultOfjb = srfUniversal(fr, tiltAngleInDegrees);//resault {0,FS,0,0,0,0,RS}
            if (maxh > resaultOfjb[1]) {
                tanJSF = fj / resaultOfjb[1];
                jsf = Math.toDegrees(Math.atan(tanJSF));
                ksj = 270 - jsf;// искомый угол поворота
                angl = tiltAngle(jsf, resaultOfjb[1], rj);
                resaultOfjb[0] = fo;
                resaultOfjb[2] = ksj;
                resaultOfjb[3] = angl;
                resaultOfjb[4] = maxh;
                resaultOfjb[5] = fr;
                return resaultOfjb;
            } else {
                resaultOfjb[6] = elseeMaxhMoreRes(rj, oj, jrd, tiltAngleInDegrees, fr, maxh);
            }
        } else {
            resaultOfjb[6] = -10000.0;
        }
        return resaultOfjb;
    }

    private double[] ojfb(double rj, double oj, double jrd, double tiltAInDegrees) {
        double jrf = 0;
        double ksj = 0;
        double[] resaultOjfb = {0, 0, 0, 0, 0, 0, 0};
        double jf = 0;
        double fo = 0;
        double fr = 0;
        double tanFSJ = 0;
        double fsj = 0;
        double maxh = 0;
        double angl = 0;
        jrf = jrd - 180;
        jf = rj * Math.tan(Math.toRadians(jrf));
        fo = oj + jf;//return this is x, one of coordinats
        if (cornerSize > fo) {
            maxh = findingMaxH(cornerSize, fo);
            fr = rj / Math.cos(Math.toRadians(jrf));
            resaultOjfb = srfUniversal(fr, tiltAInDegrees);//resault {0,FS,0,0,0,0,RS}
            if (maxh > resaultOjfb[1]) {
                tanFSJ = jf / resaultOjfb[1];
                fsj = Math.toDegrees(Math.atan(tanFSJ));
                ksj = 270 + fsj;// искомый угол поворота
                angl = tiltAngle(fsj, resaultOjfb[1], rj);
                resaultOjfb[0] = fo;
                resaultOjfb[2] = ksj;
                resaultOjfb[3] = angl;
                resaultOjfb[4] = maxh;
                resaultOjfb[5] = fr;
                return resaultOjfb;
            } else {
                resaultOjfb[6] = elseeMaxhMoreRes(rj, oj, jrd, tiltAInDegrees, fr, maxh);
            }
        } else {
            double[] dr_df = {0, 0};
            dr_df = findingDR(rj, oj, jrd, tiltAInDegrees, cornerSize);
            double dg = oj * Math.tan(Math.toRadians(jrd));
            maxh = findingMaxH(cornerSize, (dg + oj));
            double rf = dr_df[1] - dr_df[0];
            resaultOjfb[6] = findingRE(maxh, rf, dr_df[0], tiltAInDegrees);
            readiness = false;
        }
        return resaultOjfb;
    }

    private double _90180(double ao, double or, double ard, double x, double rotAng) {
        double Ao = 0;
        double X = 0;
        if (rotAng == 90) {
            Ao = ao;
            X = x;
        }
        if (rotAng == 180) {
            Ao = x;
            X = ao;
        }
        double ar = Ao - or;
        double adr = 180 - 45 - ard;
        double dr = (Ao - or) * (Math.sin(Math.toRadians(45)) / Math.sin(Math.toRadians(adr)));
        double rs = or / Math.cos(Math.toRadians(ard));
        double maxHi = findingMaxH(cornerSize, or);
        double os = or * Math.tan(Math.toRadians(ard));
        double result;
        if (maxHi > os) {
            double cs = Ao - os;
            double sce = 45 + ard;
            double se = cs * (Math.sin(Math.toRadians(45)) / Math.sin(Math.toRadians(sce)));
            result = dr + rs + se;
        } else {
            double[] dR = findingDR(X, or, rotAng, ard, cornerSize);
            double[] wayIn90 = {0, 0};
            wayIn90 = findingLR(maxHi, or, dR[0], ard);
            result = wayIn90[0] + wayIn90[1];
            readiness = false;
        }
        return result;
    }

    private double tiltAngle(double s, double fs, double rg) {
        double sg = 0;
        double tanRSG = 0;
        double rsg = 0;
        sg = fs / Math.cos(Math.toRadians(s));
        tanRSG = rg / sg;
        rsg = Math.toDegrees(Math.atan(tanRSG));
        return rsg;
    }

    private double[] findingDR(double jr, double jo, double jrd, double tiltAngleInDegrees, double r) {
        double bj = 0;
        double ga = 0;
        double[] dr = {0, 0};
        double tanJBR = 0;
        double jrb = 0;
        double jra = 0;
        double jbr = 0;
        bj = r - jo;
        jbr = Math.toDegrees(Math.atan(jr / bj));
        jrb = 90 - jbr;
        ga = r - jr;
        jra = 270 - Math.toDegrees(Math.atan(ga / jo));
        if (jrd < jrb) {
            dr[0] = jr / Math.cos(Math.toRadians(jrd));
        }
        if (jrd > jrb && jrd < jra) {
            double rbd = 0;
            double br = 0;
            double brd = 0;
            double bdr = 0;
            rbd = 45 - jbr;
            br = bj / Math.cos(Math.toRadians(jbr));
            brd = jrd - jrb;
            bdr = 180 - rbd - brd;
            dr[0] = br * (Math.sin(Math.toRadians(rbd)) / Math.sin(Math.toRadians(bdr)));
        }
        if (jrd < jrb && 0 < jrb) {
            dr[0] = jr / Math.cos(Math.toRadians(jrd));
            double jd = jr * Math.tan(Math.toRadians(jrd));
            double Do = r - (jo + jd);
            double fdo = 90 + jrd;
            double dfo = 180 - fdo - 45;
            dr[1] = Do * (Math.sin(Math.toRadians(45)) / Math.sin(Math.toRadians(dfo)));
        }
        if (jrd > jra && 270 > jrd) {
            double grd = 0;
            grd = 270 - jrd;
            dr[0] = jo / Math.cos(Math.toRadians(tiltAngleInDegrees));
            double dg = jr * Math.tan(Math.toRadians(grd));
            double Da = r - (jr + dg);
            double adf = 90 + grd;
            double dfa = 180 - adf - 45;
            dr[1] = Da * (Math.sin(Math.toRadians(45)) / Math.sin(Math.toRadians(dfa)));
        }
        if (jrd > 270 && 361 > jrd) {
            double jro = 0;
            double drg = 0;
            jro = 360 - Math.toDegrees(Math.atan(jo / jr));
            drg = jrd - 270;

            if (jrd < jro) {
                double rdg = 0;
                double dg = 0;
                double od = 0;
                double db = 0;
                double df = 0;
                double dbf = 45;
                double dfb = 180 - rdg - 45;
                rdg = 90 - drg;
                dr[0] = jo / Math.sin(Math.toRadians(rdg));
                dg = jo * (Math.sin(Math.toRadians(drg)) / Math.sin(Math.toRadians(rdg)));
                od = jr - dg;
                db = r - od;
                dr[1] = db * (Math.sin(Math.toRadians(dbf)) / Math.sin(Math.toRadians(dfb))); //(расчитывается df
            }
            if (jrd > jro && 361 > jrd) {
                double drj = 0;
                double jd = 0;
                double DO = 0;
                double ad = 0;
                double jdr = 0;
                double dfa = 0;
                jdr = drg;
                drj = 90 - drg;
                dr[0] = jr / Math.sin(Math.toRadians((90 - drj)));
                jd = dr[0] * (Math.sin(Math.toRadians(drj)) / Math.sin(Math.toRadians(90)));
                DO = jo - jd;
                ad = r - DO;
                dfa = 180 - 45 - jdr;
                dr[1] = ad * (Math.sin(Math.toRadians(45)) / Math.sin(Math.toRadians(dfa)));//(расчитывается df)
            }
        }
        return dr;
    }

    private double findingRE(double fq, double dr, double rf, double tiltAngInDeg) {
        double df = dr + rf;
        double tanQDF = fq / df;
        double qdf = Math.toDegrees(Math.atan(tanQDF));
        double der = 180 - qdf - tiltAngInDeg;
        double er = dr * (Math.sin(Math.toRadians(qdf)) / Math.sin(Math.toRadians(der)));
        return er;
    }

    private double findingMaxH(double r, double fo) {
        double h = r - fo;
        return h;
    }

    private double[] findingLR(double qf, double fr, double dr, double ird) {
        double[] lr = {0, 0};
        double fd = fr + dr;
        double tanQDF = qf / fd;
        double qdf = Math.toDegrees(Math.atan(tanQDF));
        double rid = 180 - ird - qdf;
        lr[1] = dr * (Math.sin(Math.toRadians(rid)) / Math.sin(Math.toRadians(qdf))); //ir
        double lir = 180 - rid;
        double lri = 180 - (2 * ird);
        double rli = 180 - lir - lri;
        lr[0] = lr[1] * (Math.sin(Math.toRadians(rli)) / Math.sin(Math.toRadians(lir)));
        return lr;
    }

    private double elseeMaxhMoreRes(double go, double gr, double jrd, double til, double fr, double maxh) {
        double[] lR = {0, 0};
        double[] dR = findingDR(go, gr, jrd, til, cornerSize);
        lR = findingLR(maxh, fr, dR[0], til);
        double res = lR[0];
        readiness = false;
        return res;
    }

    private double findingWay(double a, double b, double afTtheCount) {
        double ways = 0;
        if (afTtheCount != 0) {
            ways = a + b;
        } else {
            ways = way + a;
        }
        return ways;
    }

    private double[] modulationPoint(double x, double y, double jrd, double ru) {
        double[] newCoordinate = {0, 0};
        double zu = 0;
        double zr = 0;
        double zru = 0;
        double[] side = {0, 0};
        if (0 < jrd && 90 > jrd) {
            zru = 90 - jrd;
            newCoordinate[0] = x + side[0];
            newCoordinate[1] = y - side[1];
        }
        if (jrd == 180) {
            newCoordinate[0] = x - ru;
            newCoordinate[1] = y;
        }
        if (90 < jrd && 180 > jrd) {
            zru = 180 - jrd;
            side = rzu(ru, zru);
            newCoordinate[0] = x - side[0];
            newCoordinate[1] = y - side[1];
        }
        if (180 < jrd && 270 > jrd) {
            zru = jrd - 180;
            side = rzu(ru, zru);
            newCoordinate[0] = x - side[0];
            newCoordinate[1] = y + side[1];
        }
        if (270 < jrd && 360 > jrd) {
            newCoordinate[0] = x;
            newCoordinate[1] = y;
        }
        return newCoordinate;
    }

    private double[] rzu(double ur, double frj) {
        double[] z = {0, 0};
        z[1] = ur * Math.sin(Math.toRadians(frj)); //zu
        z[0] = ur * Math.cos(Math.toRadians(frj)); //zr
        return z;
    }

    private double[] findingRef(double h, double n, double aoy) {
        double sinWOP = Math.sin(Math.toRadians(aoy)) / n;
        double wop = Math.toDegrees(Math.asin(sinWOP));
        double wpo = 90 - wop;
        double op = h / Math.cos(Math.toRadians(wop));
        double wp = h * Math.tan(Math.toRadians(wop));
        double[] r = {0, 0}; // ou, s
        r[0] = 2 * wp; //ou
        r[1] = 2 * op; //op
        return r;
    }

}

