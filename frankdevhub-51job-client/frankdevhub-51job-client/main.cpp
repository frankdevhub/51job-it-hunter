#include "frankdevhub_51job_client.h"
#include <QtWidgets/QApplication>
#include <QUrl>

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);
	frankdevhub_51job_client w;
	w.show();
	return a.exec();
}
