import java.util.Scanner;

public class GeomСalculations {
    private double coordinateX = 0;
    private double coordinateY = 0;
    private double rotationAngleInDegrees = 0;//угол поворота
    private double tiltAngleInDegrees = 0;//угол наклона - падения
    private double cornerSize = 0;//the size of the verge
    private double[] afterTheCount1 = {0, 0, 0, 0, 0, 0, 0}; //x,y, угол поворота, угол наклона,maxh,fr,RS
    private double[] afterTheCount2 = {0, 0, 0, 0, 0, 0, 0}; //x,y, угол поворота, угол наклона,maxh,fr,RS
    private double[] afterTheCount3 = {0, 0, 0, 0, 0, 0, 0}; //x,y, угол поворота, угол наклона,maxh,fr,RS
    private double way = 0;
    private boolean readiness = false;
    private static final String TEXT_ENTER_X = "Enter coordinat X:";
    private static final String TEXT_ENTER_Y = "Enter coordinat Y:";
    private static final String TEXT_ENTER_TILT = "Enter the tilt angle:";
    private static final String TEXT_ENTER_ROTATION = "Enter the rotation angle:";
    private static final String TEXT_ENTER_SIZE = "Enter the sithe of the verge:";

    public void prelaunchCheck() {
        if (270 >= rotationAngleInDegrees && 0 <= rotationAngleInDegrees) {
            if (90 > tiltAngleInDegrees && 0 < tiltAngleInDegrees) {
                readiness = true;
            }
        }
    }

    public void start() {
//        for (int i = 170; i<270; i = i + 10) {
//            choiceoOfMethod(6, 3, i, 60, afterTheCount1, way);
//            System.out.println(way);
//        }
        reading();

        afterTheCount1 = choiceoOfMethod(coordinateX, coordinateY, rotationAngleInDegrees, tiltAngleInDegrees);
        double dr = findingDR(coordinateX, coordinateY, rotationAngleInDegrees, tiltAngleInDegrees, cornerSize);
        double re = findingRE(afterTheCount1[0], afterTheCount1[4], dr, afterTheCount1[5], tiltAngleInDegrees);
        way = re + afterTheCount1[6];
        System.out.println(way);

        afterTheCount2 = choiceoOfMethod(afterTheCount1[0], afterTheCount1[1], afterTheCount1[2], afterTheCount1[3]);
        way = way + afterTheCount2[6];
        System.out.println(way);

        dr = findingDR(afterTheCount2[0], afterTheCount2[1], afterTheCount2[2], afterTheCount2[3], cornerSize);
        way = findingDR(afterTheCount2[0], afterTheCount2[1], afterTheCount2[2], afterTheCount2[3], cornerSize);
        System.out.println(way);

    }

    private double[] choiceoOfMethod(double x, double y, double rotationAngle, double tiltAngle) {
        double[] resultChoice = {0, 0, 0, 0};
        if (0 == rotationAngle || 90 == rotationAngle || 180 == rotationAngle || 270 == rotationAngle || 360 == rotationAngle) {
            way = way + 0;
        }
        if (0 < rotationAngle && 90 > rotationAngle) {
            resultChoice = afgo(x, y, rotationAngle, tiltAngle); //{FS,FO,fsg,angl,maxh,fr,RS}
        }
        if (90 < rotationAngle && 180 > rotationAngle) {
            double tanORJ = 0;
            double jro = 0;
            tanORJ = y / x;
            jro = 360 - Math.toDegrees(Math.atan(tanORJ));
            if (jro > (180 + rotationAngle)) {
                resultChoice = agfo(x, y, rotationAngle, tiltAngle); //{FS,FO,fsg,angl,maxh,fr,RS}
            }
            if (jro < (180 + rotationAngle)) {
                resultChoice = ofjb(x, y, rotationAngle, tiltAngle); //{FS,FO,ksj,angl,maxh,fr,RS}
            }
        }
        if (180 < rotationAngle && 270 > rotationAngle) {
            resultChoice = ojfb(x, y, rotationAngle, tiltAngle); //{FS,FO,ksj,angl,maxh,fr,RS}
        }
        return resultChoice;
    }

    private double[] test() {
        double[] loh = {1, 2, 3, 4};
        return loh;
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
        resaultU[0] = fr * Math.tan(Math.toRadians(srf)); // return one of the coordinats fs
        resaultU[6] = fr / Math.cos(Math.toRadians(srf));//rs
        return resaultU; //resault {FS,0,0,0,0,0,RS}
    }

    private double[] afgo(double go, double gr, double jrd, double tiltAngleInDegrees) {
        double frg = 0;
        double fsg = 0;
        double[] resaultAfgo = {0, 0, 0, 0, 0, 0, 0};
        double fg = 0;
        double fo = 0;
        double fr = 0;
        double tanGSF = 0;
        double angl = 0;
        double gsf = 0;
        frg = 90 - jrd;
        fg = gr * Math.tan(Math.toRadians(frg));
        fo = fg + go; //return this is x, one of coordinats
        fr = gr / Math.cos(Math.toRadians(frg));
        resaultAfgo = srfUniversal(fr, tiltAngleInDegrees); //resault {FS,0,0,0,0,0,RS}
        double maxh = findingMaxH(cornerSize, fo);
        if (maxh > resaultAfgo[0]) {
            tanGSF = fg / resaultAfgo[0];
            gsf = Math.toDegrees(Math.atan(tanGSF));
            fsg = 360 - gsf; // искомый угол поворота
            angl = tiltAngle(gsf, resaultAfgo[1], go);
            resaultAfgo[1] = fo;
            resaultAfgo[2] = fsg;
            resaultAfgo[3] = angl;
            resaultAfgo[4] = maxh;
            resaultAfgo[5] = fr;
            return resaultAfgo;
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
        double maxh = findingMaxH(cornerSize, fo);
        fr = gr / Math.cos(Math.toRadians(grf));
        resaultAgfo = srfUniversal(fr, tiltAngleInDegrees);//resault {FS,0,0,0,0,0,RS}
        if (maxh > resaultAgfo[0]) {
            tanFSG = fg / resaultAgfo[0];
            fsg = Math.toDegrees(Math.atan(tanFSG));// искомый угол поворота
            angl = tiltAngle(fsg, resaultAgfo[1], go);
            resaultAgfo[1] = fo;
            resaultAgfo[2] = fsg;
            resaultAgfo[3] = angl;
            resaultAgfo[4] = maxh;
            resaultAgfo[5] = fr;
            return resaultAgfo;
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
        double maxh = findingMaxH(cornerSize, fo);
        fr = rj / Math.cos(Math.toRadians(frj));
        resaultOfjb = srfUniversal(fr, tiltAngleInDegrees);//resault {FS,0,0,0,0,0,RS}
        if (maxh > resaultOfjb[0]) {
            tanJSF = fj / resaultOfjb[0];
            jsf = Math.toDegrees(Math.atan(tanJSF));
            ksj = 270 - jsf;// искомый угол поворота
            angl = tiltAngle(jsf, resaultOfjb[1], rj);
            resaultOfjb[1] = fo;
            resaultOfjb[2] = ksj;
            resaultOfjb[3] = angl;
            resaultOfjb[4] = maxh;
            resaultOfjb[5] = fr;
            return resaultOfjb;
        }
        return resaultOfjb;
    }

    private double[] ojfb(double rj, double oj, double jrd, double tiltAngleInDegrees) {
        double jrf = 0;
        double ksj = 0;
        double[] resaultOjfb = {0, 0, 0, 0};
        double jf = 0;
        double fo = 0;
        double fr = 0;
        double tanFSJ = 0;
        double fsj = 0;
        double angl = 0;
        jrf = jrd - 180;
        jf = rj * Math.tan(Math.toRadians(jrf));
        fo = oj + jf;//return this is x, one of coordinats
        double maxh = findingMaxH(cornerSize, fo);
        fr = rj / Math.cos(Math.toRadians(jrf));
        resaultOjfb = srfUniversal(fr, tiltAngleInDegrees);//resault {FS,0,0,0,0,0,RS}
        if (maxh > resaultOjfb[0]) {
            tanFSJ = jf / resaultOjfb[0];
            fsj = Math.toDegrees(Math.atan(tanFSJ));
            ksj = 270 + fsj;// искомый угол поворота
            angl = tiltAngle(fsj, resaultOjfb[1], rj);
            resaultOjfb[1] = fo;
            resaultOjfb[2] = ksj;
            resaultOjfb[3] = angl;
            resaultOjfb[4] = maxh;
            resaultOjfb[5] = fr;
            return resaultOjfb;
        }
        return resaultOjfb;
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

    private double findingDR(double jr, double jo, double jrd, double tiltAngleInDegrees, double r) {
        double bj = 0;
        double ga = 0;
        double dr = 0;
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
            dr = jr / Math.cos(Math.toRadians(jrd));
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
            dr = br * (Math.sin(Math.toRadians(rbd)) / Math.sin(Math.toRadians(bdr)));
        }
        if (jrd < jrb && 0 < jrb) {
            double br = 0;
            dr = jr / Math.cos(Math.toRadians(jrd));
        }
        if (jrd > jra && 270 < jrd) {
            double grd = 0;
            grd = 270 - jrd;
            dr = jo / Math.cos(Math.toRadians(tiltAngleInDegrees));
        }
        if (jrd > 270 && 361 >jrd) {
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
                dr = jo / Math.sin(Math.toRadians(rdg));
                dg = jo * (Math.sin(Math.toRadians(drg)) / Math.sin(Math.toRadians(rdg)));
                od = jr - dg;
                db = r - od;
                dr = db * (Math.sin(Math.toRadians(dbf)) / Math.sin(Math.toRadians(dfb))); //(расчитывается df)
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
                dr = jr / Math.sin(Math.toRadians((90 - drj)));
                jd = dr * (Math.sin(Math.toRadians(drj)) / Math.sin(Math.toRadians(90)));
                DO = jo - jd;
                ad = r - DO;
                dfa = 180 - 45 - jdr;
                dr = ad * (Math.sin(Math.toRadians(45)) / Math.sin(Math.toRadians(dfa)));//(расчитывается df)
            }
        }
        return dr;
    }

    private double findingRE(double fs, double fq, double dr, double rf, double tiltAngleInDegrees) {
        double df = dr + rf;
        double tanQDF = fq / df;
        double qdf = Math.toDegrees(Math.atan(tanQDF));
        double der = 180 - qdf - tiltAngleInDegrees;
        double er = dr * (Math.sin(Math.toRadians(qdf)) / Math.sin(Math.toRadians(der)));
        return er;
    }

    private double findingMaxH(double r, double fo) {
        double h = r - fo;
        return h;
    }
}

