using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;
using WebSocket4Net;
namespace WSClient
{
    public partial class Form1 : Form
    {
        private WebSocket socket;
        
        public Form1()
        {
            InitializeComponent();
            socket = new WebSocket("ws://localhost/api/v1/ws");  
            socket.MessageReceived +=socket_MessageReceived;
        }

        private async void socket_MessageReceived(object sender, MessageReceivedEventArgs e)
        {
            Action action = () => { label2.Text = e.Message; };
            label2.BeginInvoke(action);
        }
        
        private void button1_Click(object sender, EventArgs e)
        {
            if(socket.State != WebSocketState.Open)
                socket.Open();

            socket.Send(textBox1.Text.Trim());
        }

        private void Form1_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (socket.State != WebSocketState.Open)
                socket.Close();
        }
    }
}
