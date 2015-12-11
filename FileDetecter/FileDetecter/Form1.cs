using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using System.IO;

namespace FileDetecter
{
    public partial class Form1 : Form
    {
        string Path;
        string ExePath;
        FolderBrowserDialog FDB;
        public Form1()
        {
            InitializeComponent();          
            label2.Text = "설정된 경로 없음";
            label5.Text = "설정된 경로 없음";
        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            FDB = new FolderBrowserDialog();
            // FolderBrowserDialog 생성

            if (FDB.ShowDialog() == DialogResult.OK)
            //대화상자(Dialog)를 띄운후 대화상자의 응답이 확인이라면,
            {
                Path = FDB.SelectedPath;
                //Path 변수에 선택된 폴더의 경로를 집어넣는다.
                label2.Text = Path;
                this.fileSystemWatcher1.Path = Path;
            }
        }

        private void fileSystemWatcher1_Created_1(object sender, System.IO.FileSystemEventArgs e)
        {
            listBox1.Items.Add(e.Name + "이(가) 해당경로에 생성됨");
            FileInfo FI = new FileInfo(@ExePath);
            if (ExePath != null)
            {
                if (FI.Exists == true)              
                    System.Diagnostics.Process.Start(ExePath);
                if(FI.Exists == false)
                    MessageBox.Show("연결프로그램이 해당경로에 없거나, 경로가 잘못되었습니다.");
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            OpenFileDialog myOpenFilelDialog = new OpenFileDialog();
            myOpenFilelDialog.DefaultExt = "*.*";
            myOpenFilelDialog.InitialDirectory = ".";
            myOpenFilelDialog.Filter = "응용프로그램(*.exe) |*.exe| 모든프로그램(*.*)|*.*";
            myOpenFilelDialog.FilterIndex = 1;
            myOpenFilelDialog.RestoreDirectory = true;
            // FolderBrowserDialog 생성
 
            if (myOpenFilelDialog.ShowDialog() == DialogResult.OK)
            //대화상자(Dialog)를 띄운후 대화상자의 응답이 확인이라면,
            {
                ExePath = myOpenFilelDialog.FileName;
                //Path 변수에 선택된 폴더의 경로를 집어넣는다.
                label5.Text = ExePath;
            }
        }
    }
}
