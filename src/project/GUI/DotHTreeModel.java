package project.GUI;

import javax.swing.tree.*;
import javax.swing.event.*;

public class DotHTreeModel implements TreeModel {
	
	protected DotH com;
	
	public DotHTreeModel(DotH c) {
		com = c;
	}
	
	@Override
	public Object getRoot() {
		return com;
	}

	@Override
	public Object getChild(Object parent, int index) {
		if(parent instanceof DotH) {
			DotH c = (DotH) parent;
			return c.getComponent(index);
		}
		else if(parent instanceof Component) {
			Component d = (Component) parent;
			return d.get(index);
		}
		return null;
	}

	@Override
	public int getChildCount(Object parent) {
		if(parent instanceof DotH) {
			return 2;
		}
		else if(parent instanceof Component) {
			Component d = (Component)parent;
			return d.size();
		}
		return 0;
	}

	@Override
	public boolean isLeaf(Object node) {
		// TODO Auto-generated method stub
		if(node instanceof DotH)
			return false;
		else if(node instanceof Component)
			return false;
		return true;
	}

	@Override
	public int getIndexOfChild(Object parent, Object child) {
		// TODO Auto-generated method stub
		if(parent instanceof DotH) {
			DotH c = (DotH) parent;
			return c.getIndexOf((Component)child);
		}
		else if(parent instanceof Component) {
			Component d = (Component) parent;
			return d.getIndexOf((Element)child);
		}
		return 0;
	}


	@Override
	public void valueForPathChanged(TreePath path, Object newValue) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTreeModelListener(TreeModelListener l) {
		// TODO Auto-generated method stub
		
	}
	
}
