package morefireworks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemDye;

public class FireworkDesign {

	public static List<int[]> pointsToLines(int[][] design) {
		List<int[]> lines = new ArrayList<int[]>();
		int l = design.length;
		int m = design[0].length;
		for (int i = 0; i < l; ++i) {
			for (int j = 0; j < m; ++j) {
				int c = design[i][j];
				if (c == -1) {
					continue;
				}
				int down = (j + 1 < m) ? design[i + 0][j + 1] : -1;
				int left = (i + 1 < l) ? design[i + 1][j + 0] : -1;
				int dl = (j + 1 < m && i + 1 < l) ? design[i + 1][j + 1] : -1;
				int ul = (0 <= j - 1 && i + 1 < l) ? design[i + 1][j - 1] : -1;
				double hw = l / 2.0D;
				double hh = m / 2.0D;
				if (down == c) {
					int[] line = { i, j, i, j + 1, ItemDye.dyeColors[c] };
					lines.add(line);
				}
				if (left == c) {
					int[] line = { i, j, i + 1, j, ItemDye.dyeColors[c] };
					lines.add(line);
				}
				if (dl == c) {
					int[] line = { i, j, i + 1, j + 1, ItemDye.dyeColors[c] };
					lines.add(line);
				}
				if (ul == c) {
					int[] line = { i, j, i + 1, j - 1, ItemDye.dyeColors[c] };
					lines.add(line);
				}
			}
		}
    	return lines;
	}

	public static List<int[]> loadSimpleDesignFile(File file) {
		List<int[]> list = new ArrayList<int[]>();
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			List<String> lines = new ArrayList<String>();
			int maxlen = 0;
			while(true) {
				String s = br.readLine();
				if(s == null) {
					break;
				}
				if(s.length() > maxlen) {
					maxlen = s.length();
				}
				lines.add(s);
			}

			int[][] design = new int[maxlen][lines.size()];
			for(int j = 0; j < lines.size(); ++j) {
				String line = lines.get(j);
				for(int i = 0; i < line.length(); ++i) {
					int c = -1;
					switch(line.charAt(i)) {
					case '0':c = 0;break;
					case '1':c = 1;break;
					case '2':c = 2;break;
					case '3':c = 3;break;
					case '4':c = 4;break;
					case '5':c = 5;break;
					case '6':c = 6;break;
					case '7':c = 7;break;
					case '8':c = 8;break;
					case '9':c = 9;break;
					case 'A':c = 10;break;
					case 'B':c = 11;break;
					case 'C':c = 12;break;
					case 'D':c = 13;break;
					case 'E':c = 14;break;
					case 'F':c = 15;break;
					default:
						c = -1;break;
					}
					design[i][lines.size() - 1 - j] = c;
				}

			}
			list = pointsToLines(design);
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return list;
	}
	public static List<int[]> loadDesignFile(File file) {
		List<int[]> list = new ArrayList<int[]>();
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			while(true) {
				String s = br.readLine();
				if(s == null) {
					break;
				}
				String[] tokens = s.split(",");
				if(tokens.length < 5) {
					continue;
				}

				int[] line = new int[5];
				try {
					int i = 0;
					for(String token : tokens) {
						line[i++] = Integer.parseInt(token.trim());
					}
					list.add(line);
				}
				catch(NumberFormatException e) {

				}
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return list;
	}
}
